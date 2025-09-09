package driver

import (
	"fmt"
	"ride-sharing-service/location"
)

type Driver struct {
	ID              int
	Name            string
	VehicleType     VehicleType
	LicenseNo       string
	PhoneNo         string
	IsAvailable     bool
	CurrentLocation location.Location
}

func NewDriver(id int, name string, vehicleType VehicleType, licenseNo string, phoneNo string, location location.Location) *Driver {
	return &Driver{
		ID:              id,
		Name:            name,
		VehicleType:     vehicleType,
		LicenseNo:       licenseNo,
		PhoneNo:         phoneNo,
		IsAvailable:     true,
		CurrentLocation: location,
	}
}

func (d *Driver) UpdateLocation(location location.Location) {
	d.CurrentLocation = location
}

func (d *Driver) SetAvailability(isAvailable bool) {
	d.IsAvailable = isAvailable
}

func (d *Driver) IsDriverAvailable() bool {
	return d.IsAvailable
}

func (d *Driver) GetCurrentLocation() location.Location {
	return d.CurrentLocation
}

func (d *Driver) GetVehicleType() VehicleType {
	return d.VehicleType
}

func (d *Driver) NotifyTrip(tripId int, pickUp location.Location, dropOff location.Location, distance float64) {
	fmt.Printf("Driver %d notified of trip %d from (%f, %f) to (%f, %f) covering distance %f km\n", d.ID, tripId, pickUp.Latitude, pickUp.Longitude, dropOff.Latitude, dropOff.Longitude, distance)
}

func (d *Driver) AcceptTrip() error {
	if !d.IsAvailable {
		return fmt.Errorf("driver %d is not available", d.ID)
	}
	d.IsAvailable = false
	return nil
}

func (d *Driver) EndTrip() error {
	d.IsAvailable = true
	return nil
}

func (d *Driver) IsEligibleForTrip(vehicleType VehicleType) bool {
	return d.IsAvailable && d.VehicleType == vehicleType
}
