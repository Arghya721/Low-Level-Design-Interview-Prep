package payments

import "fmt"

// OnlinePaymentStrategy implements Payment using an online gateway (simulated).
type OnlinePaymentStrategy struct{}

func NewOnlinePaymentStrategy() *OnlinePaymentStrategy {
	return &OnlinePaymentStrategy{}
}

func (o *OnlinePaymentStrategy) Pay(amount float64) bool {
	fmt.Println("Processing online payment of:", amount)
	fmt.Println("Online payment successful.")
	return true
}
