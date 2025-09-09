package payments

import "fmt"

type Payments interface {
	Pay(amount float64) error
}

type Prepaid struct{}

type Postpaid struct{}

func (p *Postpaid) Pay(amount float64) error {
	fmt.Println("Paid using postpaid method:", amount)
	return nil
}

func (p *Prepaid) Pay(amount float64) error {
	fmt.Println("Paid using prepaid method:", amount)
	return nil
}

type PaymentMethod struct {
	Strategy Payments
}

func NewPaymentMethod(strategy Payments) Payments {
	return &PaymentMethod{Strategy: strategy}
}

func (pm *PaymentMethod) Pay(amount float64) error {
	return pm.Strategy.Pay(amount)
}
