package parkinglot

import (
	"errors"
	"fmt"
	"parking-lot/floor"
	"parking-lot/payments"
	"parking-lot/ticket"
	"parking-lot/vehicle"
	"sync"
)

type parkingLot struct {
	Floors        []floor.Floor
	ActiveTickets map[string]ticket.Ticket
	SyncMutex     *sync.Mutex
}

var parkingLotInstance *parkingLot
var once sync.Once

func newParkingLot(floors []floor.Floor) *parkingLot {
	return &parkingLot{
		Floors:        floors,
		ActiveTickets: make(map[string]ticket.Ticket),
	}
}

func GetParkingLotInstance(floors []floor.Floor) *parkingLot {
	// This function should return a singleton instance of ParkingLot

	if parkingLotInstance != nil {
		return parkingLotInstance
	}
	once.Do(func() {
		if parkingLotInstance == nil {
			parkingLotInstance = newParkingLot(floors)
			parkingLotInstance.SyncMutex = &sync.Mutex{}
		}
	})
	return parkingLotInstance
}

func (pl *parkingLot) GetFloors() []floor.Floor {
	return pl.Floors
}

func (pl *parkingLot) ParkVehicle(v vehicle.Vehicle) (ticket.Ticket, error) {
	// Logic to find an available parking spot and park the vehicle

	for _, f := range pl.Floors {
		for _, ps := range f.GetParkingSpots() {
			pl.SyncMutex.Lock()
			if ps.IsSpotEmpty() && ps.GetSpotType() == v.GetVehicleType() {
				err := ps.ParkVehicle(v)
				if err != nil {
					pl.SyncMutex.Unlock()
					return ticket.Ticket{}, err
				}
				t := ticket.NewTicket(ps)
				pl.ActiveTickets[v.GetVehicleNumber()] = *t
				fmt.Printf("Vehicle %s parked in spot %s on floor %s\n", v.GetVehicleNumber(), ps.GetSpotID(), f.GetFloorID())
				pl.SyncMutex.Unlock()
				return *t, nil
			}
			pl.SyncMutex.Unlock()
		}
	}

	return ticket.Ticket{}, errors.New("no available parking spot for the vehicle")
}

func (pl *parkingLot) UnparkVehicle(v vehicle.Vehicle) error {
	t, exists := pl.ActiveTickets[v.GetVehicleNumber()]
	if !exists {
		return errors.New("vehicle not found")
	}
	ps := t.GetParkingSpot()
	err := ps.UnparkVehicle()
	if err != nil {
		return err
	}
	t.SetExitTime()
	delete(pl.ActiveTickets, v.GetVehicleNumber())

	// Payment Strategy Pattern
	switch v.GetVehicleType() {
	case vehicle.Car:
		carPaymentStrategy := payments.NewPaymentStrategy(&payments.CarPaymentStrategy{})
		carPaymentStrategy.Pay(t)
		fmt.Printf("Car payment: $%.2f\n", carPaymentStrategy.Pay(t))
	case vehicle.Motorcycle:
		motorcyclePaymentStrategy := payments.NewPaymentStrategy(&payments.MotorCyclePaymentStrategy{})
		motorcyclePaymentStrategy.Pay(t)
		fmt.Printf("Motorcycle payment: $%.2f\n", motorcyclePaymentStrategy.Pay(t))
	case vehicle.Truck:
		truckPaymentStrategy := payments.NewPaymentStrategy(&payments.TruckPaymentStrategy{})
		truckPaymentStrategy.Pay(t)
		fmt.Printf("Truck payment: $%.2f\n", truckPaymentStrategy.Pay(t))
	default:
		return errors.New("invalid vehicle type")
	}
	return nil
}
