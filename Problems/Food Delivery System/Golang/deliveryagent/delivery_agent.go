package deliveryagent

import "sync"

type DeliveryAgent struct {
	ID          int
	Name        string
	OrderID     string
	isAvailable bool
	mu          sync.RWMutex
}

func NewDeliveryAgent(id int, name string) *DeliveryAgent {
	return &DeliveryAgent{ID: id, Name: name, isAvailable: true}
}

func (d *DeliveryAgent) AssignOrder(orderID string) {
	d.mu.Lock()
	defer d.mu.Unlock()
	d.OrderID = orderID
	d.isAvailable = false
}

func (d *DeliveryAgent) CompleteOrder() {
	d.mu.Lock()
	defer d.mu.Unlock()
	d.OrderID = ""
	d.isAvailable = true
}

func (d *DeliveryAgent) IsAvailable() bool {
	d.mu.RLock()
	defer d.mu.RUnlock()
	return d.isAvailable
}
