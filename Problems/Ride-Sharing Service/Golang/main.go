package main

import (
	"ride-sharing-service/driver"
	"ride-sharing-service/location"
	"ride-sharing-service/payments"
	ridesharingservice "ride-sharing-service/ride-sharing-service"
	"ride-sharing-service/user"
	"sync"
)

func main() {
	// Create users
	user1 := user.NewUser(1, "Alice", "1234567890")
	user2 := user.NewUser(2, "Bob", "0987654321")

	// Create drivers
	driver1 := driver.NewDriver(1, "Charlie", driver.Regular, "LIC123", "1112223333", location.Location{Latitude: 12.9716, Longitude: 77.5946})
	driver2 := driver.NewDriver(2, "David", driver.Regular, "LIC456", "4445556666", location.Location{Latitude: 12.2958, Longitude: 76.6394})

	// Get the ride sharing service instance
	service := ridesharingservice.GetRideSharingServiceInstance()

	// Add users and drivers to the service
	service.AddUser(user1)
	service.AddUser(user2)
	service.AddDriver(driver1)
	service.AddDriver(driver2)

	// User 1 requests a trip
	pickUp := location.Location{Latitude: 12.9716, Longitude: 77.5946}
	dropOff := location.Location{Latitude: 12.2958, Longitude: 76.6394}
	trip1, err := service.RequestTrip(user1.ID, pickUp, dropOff, driver.Regular, payments.PaymentMethod{
		Strategy: &payments.Prepaid{},
	})
	if err != nil {
		println("Error requesting trip for user 1:", err.Error())
		return
	}

	// User 2 requests a trip
	pickUp2 := location.Location{Latitude: 12.2958, Longitude: 76.6394}
	dropOff2 := location.Location{Latitude: 11.0168, Longitude: 76.9558}
	trip2, err := service.RequestTrip(user2.ID, pickUp2, dropOff2, driver.Premium, payments.PaymentMethod{
		Strategy: &payments.Postpaid{},
	})
	if err != nil {
		println("Error requesting trip for user 2:", err.Error())
		return
	}
	var wg sync.WaitGroup
	// wg.Add(2)

	wg.Go(func() {
		// driver2 accepts trip1
		// defer wg.Done()
		err = service.AcceptTrip(trip1.ID, driver2.ID)
		if err != nil {
			println("Error accepting trip1 by driver2:", err.Error())
		}
	})

	wg.Go(func() {
		// driver1 accepts trip1
		// defer wg.Done()
		err = service.AcceptTrip(trip1.ID, driver1.ID)
		if err != nil {
			println("Error accepting trip1 by driver1:", err.Error())
		}
	})

	wg.Wait()

	// Complete trip1
	err = service.CompleteTrip(trip1.ID, 120)
	if err != nil {
		println("Error completing trip1:", err.Error())
		return
	}

	// Complete trip2
	err = service.CompleteTrip(trip2.ID, 90)
	if err != nil {
		println("Error completing trip2:", err.Error())
		return
	}
}
