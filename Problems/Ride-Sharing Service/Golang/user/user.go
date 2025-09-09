package user

import (
	"ride-sharing-service/driver"
)

type User struct {
	ID      int
	Name    string
	PhoneNo string
}

func NewUser(id int, name string, phoneNo string) *User {
	return &User{
		ID:      id,
		Name:    name,
		PhoneNo: phoneNo,
	}
}

func (u *User) NotifyTrip(driver driver.Driver, tripId int) {
	println("User", u.ID, "notified of trip", tripId, "assigned to driver", driver.Name, "with vehicle", driver.GetVehicleType())
}
