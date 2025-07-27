package main

import "fmt"

// ===== VIOLATES LSP =====
// Bird base type with fly method
type Bird struct {
	name string
}

func (b *Bird) fly() {
	fmt.Printf("%s is flying\n", b.name)
}

func (b *Bird) eat() {
	fmt.Printf("%s is eating\n", b.name)
}

// Ostrich violates LSP because it can't fly but inherits from Bird
type Ostrich struct {
	Bird
}

func (o *Ostrich) fly() {
	panic("Ostriches can't fly!")
}

// ===== FOLLOWS LSP =====
// Bird interface defines basic bird behavior
type BirdInterface interface {
	eat()
}

// FlyingBird interface extends Bird for flying birds
type FlyingBird interface {
	BirdInterface
	fly()
}

// Sparrow implements FlyingBird - can both eat and fly
type Sparrow struct {
	name string
}

func (s *Sparrow) eat() {
	fmt.Printf("%s is eating seeds\n", s.name)
}

func (s *Sparrow) fly() {
	fmt.Printf("%s is flying high\n", s.name)
}

// OstrichLSP implements only BirdInterface - cannot fly
type OstrichLSP struct {
	name string
}

func (o *OstrichLSP) eat() {
	fmt.Printf("%s is eating plants\n", o.name)
}

// ===== DEMONSTRATION FUNCTIONS =====
func demonstrateLSPViolation() {
	fmt.Println("\n=== DEMONSTRATING LSP VIOLATION ===")

	// This violates LSP - Ostrich can't fly but inherits from Bird
	ostrich := &Ostrich{Bird{name: "Ostrich"}}

	fmt.Println("Ostrich eating (works fine):")
	ostrich.eat()

	fmt.Println("Ostrich trying to fly (will panic):")
	// This will cause a panic because ostriches can't fly
	defer func() {
		if r := recover(); r != nil {
			fmt.Printf("Recovered from panic: %v\n", r)
		}
	}()
	ostrich.fly()
}

func demonstrateLSPCompliance() {
	fmt.Println("\n=== DEMONSTRATING LSP COMPLIANCE ===")

	// Create birds that follow LSP
	sparrow := &Sparrow{name: "Sparrow"}
	ostrich := &OstrichLSP{name: "Ostrich"}

	// Function that works with any bird
	feedBird := func(bird BirdInterface) {
		fmt.Printf("Feeding bird: ")
		bird.eat()
	}

	// Function that works with flying birds
	flyBird := func(bird FlyingBird) {
		fmt.Printf("Making bird fly: ")
		bird.fly()
	}

	// Both birds can be fed (they implement BirdInterface)
	fmt.Println("Feeding birds:")
	feedBird(sparrow)
	feedBird(ostrich)

	// Only sparrow can fly (it implements FlyingBird)
	fmt.Println("\nFlying birds:")
	flyBird(sparrow)
	// ostrich cannot be passed to flyBird because it doesn't implement FlyingBird
	fmt.Println("Ostrich cannot be passed to flyBird function - this is correct!")
}

func demonstrateSubstitution() {
	fmt.Println("\n=== DEMONSTRATING PROPER SUBSTITUTION ===")

	// Create a slice of flying birds
	flyingBirds := []FlyingBird{
		&Sparrow{name: "Robin"},
		&Sparrow{name: "Blue Jay"},
		&Sparrow{name: "Cardinal"},
	}

	// All flying birds can be substituted for each other
	fmt.Println("All flying birds can fly:")
	for _, bird := range flyingBirds {
		bird.fly()
	}

	// Create a slice of any birds
	allBirds := []BirdInterface{
		&Sparrow{name: "Sparrow"},
		&OstrichLSP{name: "Ostrich"},
	}

	// All birds can eat (they all implement BirdInterface)
	fmt.Println("\nAll birds can eat:")
	for _, bird := range allBirds {
		bird.eat()
	}
}

func main() {
	fmt.Println("Liskov Substitution Principle (LSP) Example")
	fmt.Println("===========================================")

	// Demonstrate LSP violation
	demonstrateLSPViolation()

	// Demonstrate LSP compliance
	demonstrateLSPCompliance()

	// Demonstrate proper substitution
	demonstrateSubstitution()

	fmt.Println("\n=== SUMMARY ===")
	fmt.Println("LSP Violation: Ostrich inherits from Bird but can't fly, causing runtime errors")
	fmt.Println("LSP Compliance: Separate interfaces for different bird capabilities")
	fmt.Println("Benefits: Type safety, proper substitution, no runtime errors")
}
