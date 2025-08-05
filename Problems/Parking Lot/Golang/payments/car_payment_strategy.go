package payments

import (
	"math"
	"parking-lot/ticket"
)

type CarPaymentStrategy struct{}

const (
	CAR_RATE_PER_HOUR = 2.0
)

func (cps *CarPaymentStrategy) pay(ticket ticket.Ticket) float64 {
	duration := ticket.GetExitTime().Sub(ticket.GetEntryTime())

	parkedHours := math.Ceil(duration.Seconds() / 3600.0)

	return parkedHours * CAR_RATE_PER_HOUR
}
