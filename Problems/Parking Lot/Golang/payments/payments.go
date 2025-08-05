package payments

import "parking-lot/ticket"

type Payments interface {
	pay(ticket ticket.Ticket) float64
}
