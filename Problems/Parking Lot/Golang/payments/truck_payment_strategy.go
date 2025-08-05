package payments

import (
	"math"
	"parking-lot/ticket"
)

type TruckPaymentStrategy struct{}

const (
	TRUCK_RATE_PER_HOUR = 3.0
)

func (tps *TruckPaymentStrategy) pay(ticket ticket.Ticket) float64 {
	duration := ticket.GetExitTime().Sub(ticket.GetEntryTime())

	parkedHours := math.Ceil(duration.Seconds() / 3600.0)

	return parkedHours * TRUCK_RATE_PER_HOUR
}
