package main

import (
	"parking-lot/floor"
	"parking-lot/parkingSpot"
	"parking-lot/parkinglot"
	"parking-lot/vehicle"
	"sync"
)

func main() { // Initialize the parking lot and other components here
	// This is where you would set up your floors, parking spots, and vehicles

	carSpot1 := parkingSpot.NewCarParkingSpot("C1")
	carSpot2 := parkingSpot.NewCarParkingSpot("C2")
	motorcycleSpot1 := parkingSpot.NewMotorcycleParkingSpot("M1")
	motorcycleSpot2 := parkingSpot.NewMotorcycleParkingSpot("M2")
	truckSpot1 := parkingSpot.NewTruckParkingSpot("T1")
	truckSpot2 := parkingSpot.NewTruckParkingSpot("T2")

	floor1 := floor.NewFloor("1", []parkingSpot.ParkingSpot{carSpot1, motorcycleSpot1, truckSpot1})
	floor2 := floor.NewFloor("2", []parkingSpot.ParkingSpot{carSpot2, motorcycleSpot2, truckSpot2})

	parkingLot := parkinglot.GetParkingLotInstance([]floor.Floor{*floor1, *floor2})

	CarVehicle := vehicle.NewCarVehicle("CAR123")
	// MotorcycleVehicle := vehicle.NewMotorcycleVehicle("MOTO123")
	// TruckVehicle := vehicle.NewTruckVehicle("TRUCK123")

	CarVehicle2 := vehicle.NewCarVehicle("CAR456")

	var wg sync.WaitGroup
	wg.Add(3) // Add the number of vehicles to be parked

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

	// _, err := parkingLot.ParkVehicle(TruckVehicle)
	// if err != nil {
	// 	println("Error parking truck:", err.Error())
	// }

	go func() {
		CarVehicle3 := vehicle.NewCarVehicle("CAR789")
		defer wg.Done()
		_, err := parkingLot.ParkVehicle(CarVehicle3)
		if err != nil {
			println("Error parking car:", err.Error())
		}
	}()
	wg.Wait()

	// MotorcycleVehicle2 := vehicle.NewMotorcycleVehicle("MOTO456")
	// _, err = parkingLot.ParkVehicle(MotorcycleVehicle2)
	// if err != nil {
	// 	println("Error parking motorcycle:", err.Error())
	// }
	println("Vehicles parked successfully!")
}
