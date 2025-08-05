package payments

import (
	"math"
	"parking-lot/ticket"
)

type MotorCyclePaymentStrategy struct{}

const (
	MOTORCYCLE_RATE_PER_HOUR = 1.0
)

func (mps *MotorCyclePaymentStrategy) pay(ticket ticket.Ticket) float64 {
	duration := ticket.GetExitTime().Sub(ticket.GetEntryTime())

	parkedHours := math.Ceil(duration.Seconds() / 3600.0)

	return parkedHours * MOTORCYCLE_RATE_PER_HOUR
}
