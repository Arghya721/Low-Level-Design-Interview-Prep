package trip

import (
	"ride-sharing-service/driver"
	"ride-sharing-service/location"
	"ride-sharing-service/payments"
	"ride-sharing-service/user"
	"time"
)

type TripStatus string

const (
	Ongoing   TripStatus = "ONGOING"
	Pending   TripStatus = "PENDING"
	Completed TripStatus = "COMPLETED"
	Cancelled TripStatus = "CANCELLED"
)

type Trip struct {
	ID              int
	User            user.User
	Driver          *driver.Driver
	PickUp          location.Location
	DropOff         location.Location
	Distance        float64
	VehicleType     driver.VehicleType
	PaymentStrategy payments.PaymentMethod
	TripStatus      TripStatus
	TimeTaken       time.Duration
}

func NewTrip(id int, u user.User, d *driver.Driver, pickUp, dropOff location.Location, distance float64, vehicleType driver.VehicleType, paymentStrategy payments.PaymentMethod) *Trip {
	return &Trip{
		ID:              id,
		User:            u,
		Driver:          d,
		PickUp:          pickUp,
		DropOff:         dropOff,
		Distance:        distance,
		PaymentStrategy: paymentStrategy,
		VehicleType:     vehicleType,
		TripStatus:      Pending,
	}
}
