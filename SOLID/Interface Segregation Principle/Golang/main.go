package main

import "fmt"

// ===== VIOLATES ISP =====
// Worker interface forces all implementations to have both work and eat methods
type Worker interface {
	work()
	eat()
}

// HumanWorker implements Worker - can both work and eat
type HumanWorker struct {
	name string
}

func (h *HumanWorker) work() {
	fmt.Printf("%s is working\n", h.name)
}

func (h *HumanWorker) eat() {
	fmt.Printf("%s is eating\n", h.name)
}

// RobotWorker violates ISP because it's forced to implement eat() even though robots don't eat
type RobotWorker struct {
	name string
}

func (r *RobotWorker) work() {
	fmt.Printf("%s is working efficiently\n", r.name)
}

func (r *RobotWorker) eat() {
	// Robots don't eat, but we're forced to implement this method
	fmt.Printf("%s doesn't need to eat (but interface forces it)\n", r.name)
}

// ===== FOLLOWS ISP =====
// Separate interfaces for different capabilities
type Workable interface {
	work()
}

type Eatable interface {
	eat()
}

// HumanWorkerISP implements both Workable and Eatable
type HumanWorkerISP struct {
	name string
}

func (h *HumanWorkerISP) work() {
	fmt.Printf("%s is working\n", h.name)
}

func (h *HumanWorkerISP) eat() {
	fmt.Printf("%s is eating\n", h.name)
}

// RobotWorkerISP implements only Workable - doesn't need to eat
type RobotWorkerISP struct {
	name string
}

func (r *RobotWorkerISP) work() {
	fmt.Printf("%s is working efficiently\n", r.name)
}

// ===== DEMONSTRATION FUNCTIONS =====
func demonstrateISPViolation() {
	fmt.Println("\n=== DEMONSTRATING ISP VIOLATION ===")

	// Create workers that violate ISP
	human := &HumanWorker{name: "John"}
	robot := &RobotWorker{name: "R2D2"}

	// Both are forced to implement the same interface
	workers := []Worker{human, robot}

	fmt.Println("All workers (forced to implement both work and eat):")
	for _, worker := range workers {
		worker.work()
		worker.eat()
		fmt.Println()
	}
}

func demonstrateISPCompliance() {
	fmt.Println("\n=== DEMONSTRATING ISP COMPLIANCE ===")

	// Create workers that follow ISP
	human := &HumanWorkerISP{name: "Alice"}
	robot := &RobotWorkerISP{name: "C3PO"}

	// Function that works with any workable entity
	assignWork := func(worker Workable) {
		fmt.Print("Assigning work: ")
		worker.work()
	}

	// Function that works with any eatable entity
	provideFood := func(eater Eatable) {
		fmt.Print("Providing food: ")
		eater.eat()
	}

	// Both can work
	fmt.Println("Assigning work to workers:")
	assignWork(human)
	assignWork(robot)

	// Only human can eat
	fmt.Println("\nProviding food:")
	provideFood(human)
	// robot cannot be passed to provideFood because it doesn't implement Eatable
	fmt.Println("Robot cannot be passed to provideFood function - this is correct!")
}

func demonstrateInterfaceComposition() {
	fmt.Println("\n=== DEMONSTRATING INTERFACE COMPOSITION ===")

	// Create a slice of workable entities
	workables := []Workable{
		&HumanWorkerISP{name: "Bob"},
		&RobotWorkerISP{name: "WALL-E"},
		&HumanWorkerISP{name: "Charlie"},
	}

	// All can work
	fmt.Println("All workable entities can work:")
	for _, worker := range workables {
		worker.work()
	}

	// Create a slice of eatable entities (only humans)
	eatables := []Eatable{
		&HumanWorkerISP{name: "David"},
		&HumanWorkerISP{name: "Eve"},
	}

	// All can eat
	fmt.Println("\nAll eatable entities can eat:")
	for _, eater := range eatables {
		eater.eat()
	}
}

func main() {
	fmt.Println("Interface Segregation Principle (ISP) Example")
	fmt.Println("============================================")

	// Demonstrate ISP violation
	demonstrateISPViolation()

	// Demonstrate ISP compliance
	demonstrateISPCompliance()

	// Demonstrate interface composition
	demonstrateInterfaceComposition()

	fmt.Println("\n=== SUMMARY ===")
	fmt.Println("ISP Violation: Worker interface forces all implementations to have work() and eat() methods")
	fmt.Println("ISP Compliance: Separate interfaces (Workable, Eatable) for different capabilities")
	fmt.Println("Benefits: No forced implementations, better separation of concerns, more flexible design")
}
