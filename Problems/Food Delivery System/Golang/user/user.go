package user

import (
	"fmt"
)

type User struct {
	ID           int
	Name         string
	Address      string
	OrderHistory []string
}

func NewUser(id int, name, address string) *User {
	return &User{ID: id, Name: name, Address: address, OrderHistory: make([]string, 0)}
}

func (u *User) AddOrderToHistory(orderID string) {
	u.OrderHistory = append(u.OrderHistory, orderID)
}

func (u *User) NotifyOrderStatusUpdate(orderID string, status string) {
	fmt.Println("Order status updated to:", status, "for order ID:", orderID)
}
