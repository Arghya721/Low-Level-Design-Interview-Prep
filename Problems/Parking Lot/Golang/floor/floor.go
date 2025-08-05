package floor

import (
	"parking-lot/parkingspot"
)

// Floor represents a parking floor with multiple parking spots
type Floor struct {
	FloorID      string
	ParkingSpots []parkingspot.ParkingSpot
}

func NewFloor(floorID string, parkingSpots []parkingspot.ParkingSpot) *Floor {
	return &Floor{
		FloorID:      floorID,
		ParkingSpots: parkingSpots,
	}
}

func (f *Floor) GetFloorID() string {
	return f.FloorID
}

func (f *Floor) GetParkingSpots() []parkingspot.ParkingSpot {
	return f.ParkingSpots
}
