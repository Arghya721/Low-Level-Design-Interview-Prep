package main

import (
	"food-delivery/deliveryagent"
	"food-delivery/fooddeliverysystem"
	"food-delivery/payments"
	"food-delivery/restaurant"
	"food-delivery/user"
)

func main() {
	system := fooddeliverysystem.GetInstance()

	item1 := restaurant.Item{ID: 1, Name: "Margherita Pizza", Price: 8.99, IsAvailable: true}
	item2 := restaurant.Item{ID: 2, Name: "Pepperoni Pizza", Price: 9.99, IsAvailable: true}
	restaurant1 := restaurant.NewRestaurant(1, "Pizza Place", []restaurant.Item{item1, item2}, true)

	item3 := restaurant.Item{ID: 3, Name: "Caesar Salad", Price: 5.99, IsAvailable: true}
	item4 := restaurant.Item{ID: 4, Name: "Garlic Bread", Price: 3.99, IsAvailable: true}
	restaurant2 := restaurant.NewRestaurant(2, "Salad Place", []restaurant.Item{item3, item4}, true)

	system.RegisterRestaurant(restaurant1)
	system.RegisterRestaurant(restaurant2)

	u := user.NewUser(1, "John Doe", "123 Main St, Springfield, USA")
	system.RegisterUser(u)

	agent1 := deliveryagent.NewDeliveryAgent(1, "Agent Smith")
	system.RegisterDeliveryAgent(agent1)

	ord, err := system.PlaceOrder(restaurant1.ID, u.ID, []restaurant.Item{item1, item2})
	if err != nil {
		panic(err)
	}

	pay := payments.NewPaymentStrategy(payments.NewOnlinePaymentStrategy())
	if err := system.Payment(ord.ID, pay); err != nil {
		panic(err)
	}

	if err := system.AssignOrderToDeliveryAgent(ord.ID); err != nil {
		panic(err)
	}

	if err := system.CompleteOrder(ord.ID, agent1.ID); err != nil {
		panic(err)
	}

	// Demonstrate some helper prints
	system.PrintAvailableRestaurants()
	system.PrintRestaurantMenu(restaurant1.ID)

	// Demonstrate cancel order (no-op since completed)
	_ = system.CancelOrder(ord.ID)
}
