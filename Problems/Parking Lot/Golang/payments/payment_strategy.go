package payments

import "parking-lot/ticket"

type PaymentStrategy struct {
	paymentStrategy Payments
}

func NewPaymentStrategy(paymentStrategy Payments) *PaymentStrategy {
	return &PaymentStrategy{paymentStrategy: paymentStrategy}
}

func (ps *PaymentStrategy) Pay(ticket ticket.Ticket) float64 {
	return ps.paymentStrategy.pay(ticket)
}
