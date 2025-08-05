package main

import (
	"parking-lot/floor"
	"parking-lot/parkinglot"
	"parking-lot/parkingspot"
	"parking-lot/vehicle"
	"sync"
	"time"
)

func main() { // Initialize the parking lot and other components here
	// This is where you would set up your floors, parking spots, and vehicles

	carSpot1 := parkingspot.NewCarParkingSpot("C1")
	carSpot2 := parkingspot.NewCarParkingSpot("C2")
	motorcycleSpot1 := parkingspot.NewMotorcycleParkingSpot("M1")
	motorcycleSpot2 := parkingspot.NewMotorcycleParkingSpot("M2")
	truckSpot1 := parkingspot.NewTruckParkingSpot("T1")
	truckSpot2 := parkingspot.NewTruckParkingSpot("T2")

	floor1 := floor.NewFloor("1", []parkingspot.ParkingSpot{carSpot1, motorcycleSpot1, truckSpot1})
	floor2 := floor.NewFloor("2", []parkingspot.ParkingSpot{carSpot2, motorcycleSpot2, truckSpot2})

	parkingLot := parkinglot.GetParkingLotInstance([]floor.Floor{*floor1, *floor2})

	CarVehicle := vehicle.NewCarVehicle("CAR123")
	// MotorcycleVehicle := vehicle.NewMotorcycleVehicle("MOTO123")
	// TruckVehicle := vehicle.NewTruckVehicle("TRUCK123")

	CarVehicle2 := vehicle.NewCarVehicle("CAR456")

	var wg sync.WaitGroup
	wg.Add(2) // Add the number of vehicles to be parked

	go func() {
		defer wg.Done()
		_, err := parkingLot.ParkVehicle(CarVehicle)
		if err != nil {
			println("Error parking car:", err.Error())
		}
	}()

	go func() {
		defer wg.Done()
		_, err := parkingLot.ParkVehicle(CarVehicle2)
		if err != nil {
			println("Error parking car:", err.Error())
		}
	}()

	wg.Wait()

	// _, err := parkingLot.ParkVehicle(TruckVehicle)
	// if err != nil {
	// 	println("Error parking truck:", err.Error())
	// }

	time.Sleep(2 * time.Second) // Simulate some delay before unparking)

	err := parkingLot.UnparkVehicle(CarVehicle2)
	if err != nil {
		println("Error unparking car:", err.Error())
	}

	// MotorcycleVehicle2 := vehicle.NewMotorcycleVehicle("MOTO456")
	// _, err = parkingLot.ParkVehicle(MotorcycleVehicle2)
	// if err != nil {
	// 	println("Error parking motorcycle:", err.Error())
	// }
	println("Vehicles parked successfully!")
}
