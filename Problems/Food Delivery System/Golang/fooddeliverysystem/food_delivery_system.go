package fooddeliverysystem

import (
	"errors"
	"fmt"
	"food-delivery/deliveryagent"
	"food-delivery/order"
	"food-delivery/payments"
	"food-delivery/restaurant"
	"food-delivery/user"
	"sync"
)

type FoodDeliverySystem struct {
	restaurants    map[int]*restaurant.Restaurant
	users          map[int]*user.User
	deliveryAgents map[int]*deliveryagent.DeliveryAgent
	orders         map[string]*order.Order
	mu             sync.RWMutex
}

var instance *FoodDeliverySystem
var once sync.Once

func GetInstance() *FoodDeliverySystem {
	once.Do(func() {
		instance = &FoodDeliverySystem{
			restaurants:    make(map[int]*restaurant.Restaurant),
			users:          make(map[int]*user.User),
			deliveryAgents: make(map[int]*deliveryagent.DeliveryAgent),
			orders:         make(map[string]*order.Order),
		}
	})
	return instance
}

func (f *FoodDeliverySystem) RegisterRestaurant(r *restaurant.Restaurant) {
	f.mu.Lock()
	defer f.mu.Unlock()
	f.restaurants[r.ID] = r
}

func (f *FoodDeliverySystem) RegisterUser(u *user.User) {
	f.mu.Lock()
	defer f.mu.Unlock()
	f.users[u.ID] = u
}

func (f *FoodDeliverySystem) RegisterDeliveryAgent(d *deliveryagent.DeliveryAgent) {
	f.mu.Lock()
	defer f.mu.Unlock()
	f.deliveryAgents[d.ID] = d
}

func (f *FoodDeliverySystem) OpenRestaurant(restaurantID int) {
	f.mu.Lock()
	defer f.mu.Unlock()
	if r, ok := f.restaurants[restaurantID]; ok {
		r.IsOpen = true
	}
}

func (f *FoodDeliverySystem) CloseRestaurant(restaurantID int) {
	f.mu.Lock()
	defer f.mu.Unlock()
	if r, ok := f.restaurants[restaurantID]; ok {
		r.IsOpen = false
	}
}

func (f *FoodDeliverySystem) PlaceOrder(restaurantID int, userID int, items []restaurant.Item) (*order.Order, error) {
	f.mu.RLock()
	r, okR := f.restaurants[restaurantID]
	u, okU := f.users[userID]
	f.mu.RUnlock()
	if !okR || !okU {
		return nil, errors.New("invalid restaurant or user")
	}
	if !r.IsOpen {
		return nil, errors.New("restaurant is closed")
	}
	// Validate items by ID and availability
	for _, it := range items {
		menuItem := r.GetItem(it.ID)
		if menuItem == nil || !menuItem.IsAvailable {
			return nil, errors.New("item not available")
		}
	}
	newOrder := order.NewOrder(userID, restaurantID, items, order.PENDING)

	f.mu.Lock()
	f.orders[newOrder.ID] = newOrder
	f.mu.Unlock()

	u.AddOrderToHistory(newOrder.ID)

	return newOrder, nil
}

func (f *FoodDeliverySystem) UpdateOrderStatus(orderID string, status order.STATUS) {
	f.mu.Lock()
	defer f.mu.Unlock()
	if o, ok := f.orders[orderID]; ok {
		o.Status = status
		if u, okU := f.users[o.UserID]; okU {
			u.NotifyOrderStatusUpdate(orderID, string(status))
		}
	}
}

func (f *FoodDeliverySystem) Payment(orderID string, payment *payments.PaymentStrategy) error {
	f.mu.RLock()
	o, ok := f.orders[orderID]
	f.mu.RUnlock()
	if !ok {
		return errors.New("order not found")
	}
	if payment == nil {
		return errors.New("no payment strategy")
	}
	if payment.ExecutePayment(o.GetTotalAmount()) {
		f.UpdateOrderStatus(orderID, order.IN_PROGRESS)
		return nil
	}
	return errors.New("payment failed")
}

func (f *FoodDeliverySystem) AssignOrderToDeliveryAgent(orderID string) error {
	f.mu.RLock()
	o, ok := f.orders[orderID]
	f.mu.RUnlock()
	if !ok {
		return errors.New("order not found")
	}
	f.mu.Lock()
	defer f.mu.Unlock()
	for _, agent := range f.deliveryAgents {
		if agent.IsAvailable() {
			agent.AssignOrder(o.ID)
			// status update will also notify user
			if oo, exists := f.orders[orderID]; exists {
				oo.Status = order.OUT_FOR_DELIVERY
				if u, okU := f.users[oo.UserID]; okU {
					u.NotifyOrderStatusUpdate(orderID, string(order.OUT_FOR_DELIVERY))
				}
			}
			return nil
		}
	}
	return errors.New("no delivery agent available")
}

func (f *FoodDeliverySystem) CompleteOrder(orderID string, deliveryAgentID int) error {
	f.mu.Lock()
	defer f.mu.Unlock()
	o, okO := f.orders[orderID]
	agent, okA := f.deliveryAgents[deliveryAgentID]
	if !okO || !okA || agent.IsAvailable() {
		return errors.New("invalid order or delivery agent")
	}
	agent.CompleteOrder()
	o.Status = order.COMPLETED
	if u, okU := f.users[o.UserID]; okU {
		u.NotifyOrderStatusUpdate(orderID, string(order.COMPLETED))
	}
	return nil
}

func (f *FoodDeliverySystem) PrintAvailableRestaurants() {
	f.mu.RLock()
	defer f.mu.RUnlock()
	for _, r := range f.restaurants {
		if r.IsOpen {
			fmt.Println("Available Restaurant:", r.Name, "(ID:", r.ID, ")")
		}
	}
}

func (f *FoodDeliverySystem) PrintRestaurantMenu(restaurantID int) {
	f.mu.RLock()
	r, ok := f.restaurants[restaurantID]
	f.mu.RUnlock()
	if !ok {
		fmt.Println("Restaurant not found.")
		return
	}
	fmt.Println("Menu for Restaurant:", r.Name)
	for _, item := range r.Menu {
		fmt.Println(" -", item.Name+":", item.Price)
	}
}

func (f *FoodDeliverySystem) CancelOrder(orderID string) error {
	f.mu.Lock()
	defer f.mu.Unlock()
	if o, ok := f.orders[orderID]; ok {
		o.Status = order.CANCELLED
		if u, okU := f.users[o.UserID]; okU {
			u.NotifyOrderStatusUpdate(orderID, string(order.CANCELLED))
		}
		return nil
	}
	return errors.New("order not found")
}
