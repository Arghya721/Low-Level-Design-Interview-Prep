package vehicle

import (
	"errors"
	"fmt"
)

type Vehicle interface {
	GetVehicleType() VehicleType
	GetVehicleNumber() string
}

type CarVehicle struct {
	VehicleType   VehicleType
	VehicleNumber string
}

func NewCarVehicle(vehicleNumber string) *CarVehicle {
	return &CarVehicle{
		VehicleType:   Car,
		VehicleNumber: vehicleNumber,
	}
}

func (c *CarVehicle) GetVehicleType() VehicleType {
	return c.VehicleType
}

func (c *CarVehicle) GetVehicleNumber() string {
	return c.VehicleNumber
}

type MotorcycleVehicle struct {
	VehicleType   VehicleType
	VehicleNumber string
}

func NewMotorcycleVehicle(vehicleNumber string) *MotorcycleVehicle {
	return &MotorcycleVehicle{
		VehicleType:   Motorcycle,
		VehicleNumber: vehicleNumber,
	}
}

func (m *MotorcycleVehicle) GetVehicleType() VehicleType {
	return m.VehicleType
}

func (m *MotorcycleVehicle) GetVehicleNumber() string {
	return m.VehicleNumber
}

type TruckVehicle struct {
	VehicleType   VehicleType
	VehicleNumber string
}

func NewTruckVehicle(vehicleNumber string) *TruckVehicle {
	return &TruckVehicle{
		VehicleType:   Truck,
		VehicleNumber: vehicleNumber,
	}
}

func (t *TruckVehicle) GetVehicleType() VehicleType {
	return t.VehicleType
}

func (t *TruckVehicle) GetVehicleNumber() string {
	return t.VehicleNumber
}

type VehicleFactory struct{}

func (vf *VehicleFactory) CreateVehicle(vehicleType VehicleType, vehicleNumber string) (Vehicle, error) {
	switch vehicleType {
	case Car:
		return NewCarVehicle(vehicleNumber), nil
	case Motorcycle:
		return NewMotorcycleVehicle(vehicleNumber), nil
	case Truck:
		return NewTruckVehicle(vehicleNumber), nil
	default:
		return nil, errors.New(fmt.Sprintf("unknown vehicle type: %s", vehicleType))
	}
}
