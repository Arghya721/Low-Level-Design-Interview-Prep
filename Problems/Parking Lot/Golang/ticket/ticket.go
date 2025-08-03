package ticket

import (
	parkingspot "parking-lot/parkingSpot"
	"time"

	"github.com/google/uuid"
)

type Ticket struct {
	TicketID    string
	ParkingSpot parkingspot.ParkingSpot
	EntryTime   time.Time
	ExitTime    time.Time
}

func NewTicket(parkingSpot parkingspot.ParkingSpot) *Ticket {

	return &Ticket{
		TicketID:    uuid.New().String(),
		ParkingSpot: parkingSpot,
		EntryTime:   time.Now(),
	}
}

func (t *Ticket) GetTicketID() string {
	return t.TicketID
}

func (t *Ticket) GetParkingSpot() parkingspot.ParkingSpot {
	return t.ParkingSpot
}

func (t *Ticket) GetEntryTime() time.Time {
	return t.EntryTime
}

func (t *Ticket) GetExitTime() time.Time {
	return t.ExitTime
}

func (t *Ticket) SetExitTime() {
	t.ExitTime = time.Now()
}
