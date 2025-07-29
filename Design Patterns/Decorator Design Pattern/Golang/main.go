package main

import (
	"fmt"
)

// BasePizzaInterface defines the methods that a pizza should implement
type BasePizzaInterface interface {
	cost() int
}

// FarmHouse implements BasePizzaInterface
type FarmHouse struct{}

// cost returns the cost of the FarmHouse pizza
func (f *FarmHouse) cost() int {
	return 250
}

// Margherita implements BasePizzaInterface
type Margherita struct{}

// cost returns the cost of the Margherita pizza
func (m *Margherita) cost() int {
	return 200
}

// CheeseBurst is a decorator that adds cheese to the pizza
type CheeseBurst struct {
	basePizza BasePizzaInterface
}

// NewCheeseBurstConstructor creates a new CheeseBurst decorator
func NewCheeseBurstConstructor(basePizza BasePizzaInterface) *CheeseBurst {
	return &CheeseBurst{basePizza: basePizza}
}

// cost returns the cost of the pizza with cheese burst
func (c *CheeseBurst) cost() int {
	return c.basePizza.cost() + 40
}

// Mushroom is a decorator that adds mushrooms to the pizza
type Mushroom struct {
	basePizza BasePizzaInterface
}

// NewMushroomConstructor creates a new Mushroom decorator
func NewMushroomConstructor(basePizza BasePizzaInterface) *Mushroom {
	return &Mushroom{basePizza: basePizza}
}

// cost returns the cost of the pizza with mushrooms
func (m *Mushroom) cost() int {
	return m.basePizza.cost() + 30
}

func main() {

	// make a margherita with mushroom topping
	margheritaWithMushroom := NewCheeseBurstConstructor(&Margherita{})

	fmt.Println("Cost of Margherita with Cheese Burst:", margheritaWithMushroom.cost())

	// make a farmhouse with mushroom and cheese burst topping
	farmHouseWithMushroomAndCheese := NewCheeseBurstConstructor(NewMushroomConstructor(&FarmHouse{}))

	fmt.Println("Cost of FarmHouse with Mushroom and Cheese Burst:", farmHouseWithMushroomAndCheese.cost())
}
