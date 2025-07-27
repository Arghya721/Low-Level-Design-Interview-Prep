package main

import "fmt"

// PaymentMethod is the interface that defines a payment method
type PaymentMethod interface {
	Pay()
}

// CreditCard implements the PaymentMethod interface
type CreditCard struct{}

func (c CreditCard) Pay() {
	fmt.Println("Paid using Credit Card")
}

// PayPal implements the PaymentMethod interface
type PayPal struct{}

func (p PayPal) Pay() {
	fmt.Println("Paid using PayPal")
}

// ProcessPayment uses the PaymentMethod interface
func ProcessPayment(p PaymentMethod) {
	p.Pay()
}

func main() {
	var method PaymentMethod

	// Using CreditCard
	method = CreditCard{}
	ProcessPayment(method)

	// Using PayPal
	method = PayPal{}
	ProcessPayment(method)
}
