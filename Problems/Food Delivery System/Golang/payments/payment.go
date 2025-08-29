package payments

// Payment defines the strategy interface for making a payment.
type Payment interface {
	Pay(amount float64) bool
}
