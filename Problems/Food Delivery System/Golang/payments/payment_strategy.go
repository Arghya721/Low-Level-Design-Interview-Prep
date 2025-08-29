package payments

// PaymentStrategy wraps a Payment strategy and exposes a single ExecutePayment method.
type PaymentStrategy struct {
	payment Payment
}

func NewPaymentStrategy(payment Payment) *PaymentStrategy {
	return &PaymentStrategy{payment: payment}
}

func (ps *PaymentStrategy) ExecutePayment(amount float64) bool {
	if ps == nil || ps.payment == nil {
		return false
	}
	return ps.payment.Pay(amount)
}
