package main

import (
	"fmt"
)

// Singleton structure
type singleton struct {
	data string
}

// instance holds the single instance of Singleton
var instance *singleton = &singleton{
	data: "Singleton Instance",
}

// GetInstance returns the single instance of Singleton
func GetInstance() *singleton {
	return instance
}

// main function to demonstrate the Singleton pattern
func main() {
	singletonInstance1 := GetInstance()
	singletonInstance2 := GetInstance()

	fmt.Println(singletonInstance1 == singletonInstance2)
	fmt.Println(singletonInstance2.data)
}
