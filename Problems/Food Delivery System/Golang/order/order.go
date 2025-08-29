package order

import (
	"food-delivery/restaurant"

	"github.com/google/uuid"
)

type Order struct {
	ID           string
	UserID       int
	RestaurantID int
	OrderedItems []restaurant.Item
	Status       STATUS
}

func NewOrder(userID int, restaurantID int, items []restaurant.Item, status STATUS) *Order {
	return &Order{
		ID:           uuid.NewString(),
		UserID:       userID,
		RestaurantID: restaurantID,
		OrderedItems: items,
		Status:       status,
	}
}

func (o *Order) GetTotalAmount() float64 {
	total := 0.0
	for _, it := range o.OrderedItems {
		total += it.Price
	}
	return total
}
