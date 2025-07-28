package main

import (
	"fmt"
)

// Shape interface defines a method for drawing shapes
type Shape interface {
	Draw()
}

// Circle struct implements the Shape interface
type Circle struct{}

// draw method for Circle
func (c Circle) Draw() {
	fmt.Println("Drawing a Circle")
}

// Square struct implements the Shape interface
type Square struct{}

// draw method for Square
func (s Square) Draw() {
	fmt.Println("Drawing a Square")
}

// ShapeFactory struct is responsible for creating shapes
type ShapeFactory struct{}

// getShape method returns a Shape based on the type provided
func (sf ShapeFactory) getShape(shapeType string) Shape {
	switch shapeType {
	case "CIRCLE":
		return Circle{}
	case "SQUARE":
		return Square{}
	default:
		return nil
	}
}

// main function to demonstrate the Factory Pattern
func main() {
	factory := ShapeFactory{}

	circle := factory.getShape("CIRCLE")
	circle.Draw()

	square := factory.getShape("SQUARE")
	square.Draw()
}
