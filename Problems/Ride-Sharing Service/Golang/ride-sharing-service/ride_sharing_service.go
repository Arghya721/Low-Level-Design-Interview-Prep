package ridesharingservice

import (
	"fmt"
	"math"
	"ride-sharing-service/driver"
	"ride-sharing-service/location"
	"ride-sharing-service/payments"
	"ride-sharing-service/trip"
	"ride-sharing-service/user"
	"sync"
	"time"
)

type rideSharingService struct {
	Users     map[int]*user.User
	Drivers   map[int]*driver.Driver
	Trips     map[int]*trip.Trip
	SyncMutex *sync.RWMutex
}

var serviceInstance *rideSharingService
var once sync.Once

func GetRideSharingServiceInstance() *rideSharingService {
	once.Do(func() {
		if serviceInstance == nil {
			serviceInstance = &rideSharingService{
				Users:     make(map[int]*user.User),
				Drivers:   make(map[int]*driver.Driver),
				Trips:     make(map[int]*trip.Trip),
				SyncMutex: &sync.RWMutex{},
			}
		}
	})
	return serviceInstance
}

func (r *rideSharingService) AddUser(u *user.User) {
	r.Users[u.ID] = u
}

func (r *rideSharingService) AddDriver(d *driver.Driver) {
	r.Drivers[d.ID] = d
}

func (r *rideSharingService) AddTrip(t *trip.Trip) {
	r.Trips[t.ID] = t
}

func (r *rideSharingService) notifyDrivers(trip *trip.Trip) {
	for _, d := range r.Drivers {
		if d.IsEligibleForTrip(trip.VehicleType) {
			d.NotifyTrip(trip.ID, trip.PickUp, trip.DropOff, trip.Distance)
		}
	}
}

func (r *rideSharingService) RequestTrip(userId int, pickUp, dropOff location.Location, vehicleType driver.VehicleType, paymentMethod payments.PaymentMethod) (*trip.Trip, error) {
	u, ok := r.Users[userId]
	if !ok {
		return nil, fmt.Errorf("user %d not found", userId)
	}

	newTrip := trip.NewTrip(len(r.Trips)+1, *u, nil, pickUp, dropOff, planeDistanceMeters(pickUp, dropOff)/1000, vehicleType, paymentMethod)

	// notify the selected vehicle type drivers about the new trip request
	r.notifyDrivers(newTrip)

	r.AddTrip(newTrip)
	return newTrip, nil
}

func planeDistanceMeters(loc1 location.Location, loc2 location.Location) float64 {
	const metersPerDeg = 111320.0
	phiMean := (loc1.Latitude + loc2.Latitude) * math.Pi / 360.0 // mean lat in radians
	dx := (loc2.Longitude - loc1.Longitude) * math.Cos(phiMean) * metersPerDeg
	dy := (loc2.Latitude - loc1.Latitude) * metersPerDeg
	return math.Hypot(dx, dy) // sqrt(dx^2 + dy^2)
}

func (r *rideSharingService) AcceptTrip(tripId int, driverId int) (err error) {
	t, ok := r.Trips[tripId]
	if !ok {
		return fmt.Errorf("trip %d not found", tripId)
	}

	r.SyncMutex.Lock()
	defer r.SyncMutex.Unlock()

	if t.Driver != nil {
		return fmt.Errorf("trip %d already assigned to driver %d", tripId, t.Driver.ID)

	}

	d, ok := r.Drivers[driverId]
	if !ok {
		return fmt.Errorf("driver %d not found", driverId)
	}

	if d.IsEligibleForTrip(t.VehicleType) {
		err = d.AcceptTrip()
		if err != nil {
			return err
		}
		t.TripStatus = trip.Ongoing
		t.Driver = d
		t.User.NotifyTrip(*d, t.ID)
	} else {
		return fmt.Errorf("driver %d is not eligible for trip %d", driverId, tripId)
	}

	return err
}

func (r *rideSharingService) CancelTrip(tripId int) error {
	t, ok := r.Trips[tripId]

	if !ok {
		return fmt.Errorf("trip %d not found", tripId)
	}

	if t.TripStatus != trip.Ongoing {
		return fmt.Errorf("trip %d cannot be cancelled as it is not in ONGOING status", tripId)
	}

	t.TripStatus = trip.Cancelled
	return nil
}

func (r *rideSharingService) CompleteTrip(tripId int, timeTakenInMinutes int) error {
	t, ok := r.Trips[tripId]

	if !ok {
		return fmt.Errorf("trip %d not found", tripId)
	}

	if t.TripStatus != trip.Ongoing {
		return fmt.Errorf("trip %d cannot be ended as it is not in ONGOING status", tripId)
	}

	t.TripStatus = trip.Completed
	t.TimeTaken = (time.Duration(timeTakenInMinutes) * time.Minute)

	if t.Driver != nil {
		err := t.Driver.EndTrip()
		if err != nil {
			return err
		}
	}

	// calculate fare
	fare := t.Distance * float64(t.TimeTaken/time.Minute) * 1.5

	// process payment
	err := t.PaymentStrategy.Pay(fare)
	if err != nil {
		return err
	}

	return nil
}
