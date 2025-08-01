package main

import (
	"fmt"
)

// PayStrategy is the strategy interface for payment methods
type PayStrategy interface {
	Pay()
}

// CreditCardPayment is a concrete strategy
type CreditCardPayment struct{}

// Pay method for CreditCardPayment
func (ccd CreditCardPayment) Pay() {
	fmt.Println("Payment done using Credit Card")
}

// UpiPayment is another concrete strategy
type UpiPayment struct{}

// Pay method for UpiPayment
func (upi UpiPayment) Pay() {
	fmt.Println("Payment done using UPI")
}

// paymentContext is the context that uses the strategy
type paymentContext struct {
	amount int
	method PayStrategy
}

// NewPaymentConstructor is a constructor for paymentContext
func NewPaymentConstructor(amount int, method PayStrategy) *paymentContext {
	return &paymentContext{
		amount: amount,
		method: method,
	}
}

// Pay processes the payment using the selected strategy
func (p *paymentContext) Pay() {
	fmt.Printf("Processing payment of amount: %d\n", p.amount)
	p.method.Pay()
}

func main() {
	creditCard := CreditCardPayment{}
	payment1 := NewPaymentConstructor(100, &creditCard)
	payment1.Pay()

	upipayment := UpiPayment{}
	payment2 := NewPaymentConstructor(200, &upipayment)
	payment2.Pay()
}
