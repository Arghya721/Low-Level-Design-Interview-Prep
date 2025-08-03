package parkingSpot

import (
	"errors"
	"parking-lot/vehicle"
)

type ParkingSpot interface {
	GetSpotID() string
	GetSpotType() vehicle.VehicleType
	IsSpotEmpty() bool
	GetVehicle() vehicle.Vehicle
	ParkVehicle(vehicle.Vehicle) error
	UnparkVehicle() error
}

type CarParkingSpot struct {
	SpotID   string
	SpotType vehicle.VehicleType
	IsEmpty  bool
	Vehicle  vehicle.Vehicle
}

func (cp *CarParkingSpot) GetSpotID() string {
	return cp.SpotID
}

func (cp *CarParkingSpot) GetSpotType() vehicle.VehicleType {
	return cp.SpotType
}

func (cp *CarParkingSpot) IsSpotEmpty() bool {
	return cp.IsEmpty
}

func (cp *CarParkingSpot) GetVehicle() vehicle.Vehicle {
	return cp.Vehicle
}

func (cp *CarParkingSpot) ParkVehicle(v vehicle.Vehicle) error {
	if !cp.IsEmpty {
		return errors.New("parking spot is already occupied")
	}
	cp.Vehicle = v
	cp.IsEmpty = false
	return nil
}

func (cp *CarParkingSpot) UnparkVehicle() error {
	if cp.IsEmpty {
		return errors.New("parking spot is already empty")
	}
	cp.Vehicle = nil
	cp.IsEmpty = true
	return nil
}

func NewCarParkingSpot(spotID string) *CarParkingSpot {
	return &CarParkingSpot{
		SpotID:   spotID,
		SpotType: vehicle.Car,
		IsEmpty:  true,
	}
}

type MotorcycleParkingSpot struct {
	SpotID   string
	SpotType vehicle.VehicleType
	IsEmpty  bool
	Vehicle  vehicle.Vehicle
}

func (mp *MotorcycleParkingSpot) GetSpotID() string {
	return mp.SpotID
}

func (mp *MotorcycleParkingSpot) GetSpotType() vehicle.VehicleType {
	return mp.SpotType
}

func (mp *MotorcycleParkingSpot) IsSpotEmpty() bool {
	return mp.IsEmpty
}
func (mp *MotorcycleParkingSpot) GetVehicle() vehicle.Vehicle {
	return mp.Vehicle
}

func (mp *MotorcycleParkingSpot) ParkVehicle(v vehicle.Vehicle) error {
	if !mp.IsEmpty {
		return errors.New("motorcycle parking spot is already occupied")
	}
	mp.Vehicle = v
	mp.IsEmpty = false
	return nil
}

func (mp *MotorcycleParkingSpot) UnparkVehicle() error {
	if mp.IsEmpty {
		return errors.New("motorcycle parking spot is already empty")
	}
	mp.Vehicle = nil
	mp.IsEmpty = true
	return nil
}

func NewMotorcycleParkingSpot(spotID string) *MotorcycleParkingSpot {
	return &MotorcycleParkingSpot{
		SpotID:   spotID,
		SpotType: vehicle.Motorcycle,
		IsEmpty:  true,
	}
}

type TruckParkingSpot struct {
	SpotID   string
	SpotType vehicle.VehicleType
	IsEmpty  bool
	Vehicle  vehicle.Vehicle
}

func (tp *TruckParkingSpot) GetSpotID() string {
	return tp.SpotID
}

func (tp *TruckParkingSpot) GetSpotType() vehicle.VehicleType {
	return tp.SpotType
}

func (tp *TruckParkingSpot) IsSpotEmpty() bool {
	return tp.IsEmpty
}

func (tp *TruckParkingSpot) GetVehicle() vehicle.Vehicle {
	return tp.Vehicle
}

func (tp *TruckParkingSpot) ParkVehicle(v vehicle.Vehicle) error {
	if !tp.IsEmpty {
		return errors.New("truck parking spot is already occupied")
	}
	tp.Vehicle = v
	tp.IsEmpty = false
	return nil
}

func (tp *TruckParkingSpot) UnparkVehicle() error {
	if tp.IsEmpty {
		return errors.New("truck parking spot is already empty")
	}
	tp.Vehicle = nil
	tp.IsEmpty = true
	return nil
}

func NewTruckParkingSpot(spotID string) *TruckParkingSpot {
	return &TruckParkingSpot{
		SpotID:   spotID,
		SpotType: vehicle.Truck,
		IsEmpty:  true,
	}
}

type ParkingSpotFactory struct{}

func (psf *ParkingSpotFactory) CreateParkingSpot(spotType vehicle.VehicleType, spotID string) (ParkingSpot, error) {
	switch spotType {
	case vehicle.Car:
		return NewCarParkingSpot(spotID), nil
	case vehicle.Motorcycle:
		return NewMotorcycleParkingSpot(spotID), nil
	case vehicle.Truck:
		return NewTruckParkingSpot(spotID), nil
	default:
		return nil, errors.New("invalid vehicle type")
	}
}
