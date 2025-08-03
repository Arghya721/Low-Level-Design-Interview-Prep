package floor

import (
	"parking-lot/parkingSpot"
)

// Floor represents a parking floor with multiple parking spots
type Floor struct {
	FloorID      string
	ParkingSpots []parkingSpot.ParkingSpot
}

func NewFloor(floorID string, parkingSpots []parkingSpot.ParkingSpot) *Floor {
	return &Floor{
		FloorID:      floorID,
		ParkingSpots: parkingSpots,
	}
}

func (f *Floor) GetFloorID() string {
	return f.FloorID
}

func (f *Floor) GetParkingSpots() []parkingSpot.ParkingSpot {
	return f.ParkingSpots
}
