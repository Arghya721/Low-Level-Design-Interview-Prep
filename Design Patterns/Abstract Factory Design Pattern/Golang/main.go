package main

import (
	"fmt"
)

// Vehicle interface with a method to calculate average mileage
type Vechicle interface {
	averageMileage()
}

// BMW struct implementing the Vehicle interface
type BMW struct{}

// averageMileage method for BMW
func (b BMW) averageMileage() {
	fmt.Println("BMW average mileage is 15 km/l")
}

// Mercedes struct implementing the Vehicle interface
type Mercedes struct{}

// averageMileage method for Mercedes
func (m Mercedes) averageMileage() {
	fmt.Println("Mercedes average mileage is 12 km/l")
}

// Suzuki struct implementing the Vehicle interface
type Suzuki struct{}

// averageMileage method for Suzuki
func (s Suzuki) averageMileage() {
	fmt.Println("Suzuki average mileage is 18 km/l")
}

// Hyundai struct implementing the Vehicle interface
type Hyundai struct{}

// averageMileage method for Hyundai
func (h Hyundai) averageMileage() {
	fmt.Println("Hyundai average mileage is 16 km/l")
}

// VehicleFactory Interface for creating vehicles
type VehicleFactory interface {
	createVehicle(vehicleType string) Vechicle
}

// LuxuryVehicleFactory struct implementing VehicleFactory
type LuxuryVehicleFactory struct{}

// createVehicle method for LuxuryVehicleFactory
func (f LuxuryVehicleFactory) createVehicle(vehicleType string) Vechicle {
	switch vehicleType {
	case "BMW":
		fmt.Println("Creating a BMW vehicle")
		return BMW{}
	case "Mercedes":
		fmt.Println("Creating a Mercedes vehicle")
		return Mercedes{}
	default:
		return nil
	}
}

// OrdinaryVehicleFactory struct implementing VehicleFactory
type OrdinaryVehicleFactory struct{}

// createVehicle method for OrdinaryVehicleFactory
func (f OrdinaryVehicleFactory) createVehicle(vehicleType string) Vechicle {
	switch vehicleType {
	case "Suzuki":
		fmt.Println("Creating a Suzuki vehicle")
		return Suzuki{}
	case "Hyundai":
		fmt.Println("Creating a Hyundai vehicle")
		return Hyundai{}
	default:
		return nil
	}
}

// main function to demonstrate the factory pattern
func main() {
	luxuryFactory := LuxuryVehicleFactory{}

	// Create a BMW vehicle
	luxuryFactory.createVehicle("BMW").averageMileage()

	// Create a Mercedes vehicle
	luxuryFactory.createVehicle("Mercedes").averageMileage()

	ordinaryFactory := OrdinaryVehicleFactory{}

	// Create a Suzuki vehicle
	ordinaryFactory.createVehicle("Suzuki").averageMileage()

	// Create a Hyundai vehicle
	ordinaryFactory.createVehicle("Hyundai").averageMileage()
}
