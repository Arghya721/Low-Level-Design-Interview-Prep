package main

import "fmt"

// ===== VIOLATES DIP =====
// LightBulb is a concrete low-level module
type LightBulb struct {
	name string
}

func (l *LightBulb) turnOn() {
	fmt.Printf("%s light bulb is turned on\n", l.name)
}

func (l *LightBulb) turnOff() {
	fmt.Printf("%s light bulb is turned off\n", l.name)
}

// Switch depends directly on LightBulb (high-level depends on low-level)
type Switch struct {
	bulb *LightBulb
}

func NewSwitch() *Switch {
	return &Switch{
		bulb: &LightBulb{name: "Living Room"},
	}
}

func (s *Switch) operate() {
	s.bulb.turnOn()
}

// ===== FOLLOWS DIP =====
// Switchable is an abstraction (interface) that both high and low level modules depend on
type Switchable interface {
	turnOn()
	turnOff()
}

// LightBulbDIP implements Switchable interface
type LightBulbDIP struct {
	name string
}

func NewLightBulbDIP(name string) *LightBulbDIP {
	return &LightBulbDIP{name: name}
}

func (l *LightBulbDIP) turnOn() {
	fmt.Printf("%s light bulb is turned on\n", l.name)
}

func (l *LightBulbDIP) turnOff() {
	fmt.Printf("%s light bulb is turned off\n", l.name)
}

// Fan implements Switchable interface
type Fan struct {
	name string
}

func NewFan(name string) *Fan {
	return &Fan{name: name}
}

func (f *Fan) turnOn() {
	fmt.Printf("%s fan is turned on\n", f.name)
}

func (f *Fan) turnOff() {
	fmt.Printf("%s fan is turned off\n", f.name)
}

// TV implements Switchable interface
type TV struct {
	name string
}

func NewTV(name string) *TV {
	return &TV{name: name}
}

func (t *TV) turnOn() {
	fmt.Printf("%s TV is turned on\n", t.name)
}

func (t *TV) turnOff() {
	fmt.Printf("%s TV is turned off\n", t.name)
}

// SwitchDIP depends on abstraction (Switchable interface), not concrete implementation
type SwitchDIP struct {
	device Switchable
}

func NewSwitchDIP(device Switchable) *SwitchDIP {
	return &SwitchDIP{device: device}
}

func (s *SwitchDIP) operate() {
	s.device.turnOn()
}

func (s *SwitchDIP) turnOff() {
	s.device.turnOff()
}

// ===== DEMONSTRATION FUNCTIONS =====
func demonstrateDIPViolation() {
	fmt.Println("\n=== DEMONSTRATING DIP VIOLATION ===")

	// Switch is tightly coupled to LightBulb
	sw := NewSwitch()
	fmt.Println("Switch operating (can only control LightBulb):")
	sw.operate()

	fmt.Println("\nProblem: Switch can only work with LightBulb, not other devices")
}

func demonstrateDIPCompliance() {
	fmt.Println("\n=== DEMONSTRATING DIP COMPLIANCE ===")

	// Create different devices
	lightBulb := NewLightBulbDIP("Kitchen")
	fan := NewFan("Bedroom")
	tv := NewTV("Living Room")

	// Create switches that can control any switchable device
	lightSwitch := NewSwitchDIP(lightBulb)
	fanSwitch := NewSwitchDIP(fan)
	tvSwitch := NewSwitchDIP(tv)

	// All switches can operate any device
	fmt.Println("Operating different devices:")
	lightSwitch.operate()
	fanSwitch.operate()
	tvSwitch.operate()

	fmt.Println("\nTurning off devices:")
	lightSwitch.turnOff()
	fanSwitch.turnOff()
	tvSwitch.turnOff()
}

func demonstrateFlexibility() {
	fmt.Println("\n=== DEMONSTRATING FLEXIBILITY ===")

	// Create a slice of different devices
	devices := []Switchable{
		NewLightBulbDIP("Hallway"),
		NewFan("Office"),
		NewTV("Den"),
		NewLightBulbDIP("Garage"),
	}

	// Create switches for each device
	switches := make([]*SwitchDIP, len(devices))
	for i, device := range devices {
		switches[i] = NewSwitchDIP(device)
	}

	// Operate all devices
	fmt.Println("Operating all devices:")
	for i, sw := range switches {
		fmt.Printf("Switch %d: ", i+1)
		sw.operate()
	}

	// Turn off all devices
	fmt.Println("\nTurning off all devices:")
	for i, sw := range switches {
		fmt.Printf("Switch %d: ", i+1)
		sw.turnOff()
	}
}

func demonstrateTesting() {
	fmt.Println("\n=== DEMONSTRATING TESTING BENEFITS ===")

	// Mock device for testing
	mockDevice := &MockDevice{name: "Test Device"}
	testSwitch := NewSwitchDIP(mockDevice)

	fmt.Println("Testing switch with mock device:")
	testSwitch.operate()
	testSwitch.turnOff()
}

// MockDevice for testing (implements Switchable interface)
type MockDevice struct {
	name string
}

func (m *MockDevice) turnOn() {
	fmt.Printf("MOCK: %s is turned on (for testing)\n", m.name)
}

func (m *MockDevice) turnOff() {
	fmt.Printf("MOCK: %s is turned off (for testing)\n", m.name)
}

func main() {
	fmt.Println("Dependency Inversion Principle (DIP) Example")
	fmt.Println("===========================================")

	// Demonstrate DIP violation
	demonstrateDIPViolation()

	// Demonstrate DIP compliance
	demonstrateDIPCompliance()

	// Demonstrate flexibility
	demonstrateFlexibility()

	// Demonstrate testing benefits
	demonstrateTesting()

	fmt.Println("\n=== SUMMARY ===")
	fmt.Println("DIP Violation: High-level module (Switch) depends directly on low-level module (LightBulb)")
	fmt.Println("DIP Compliance: Both high and low level modules depend on abstraction (Switchable interface)")
	fmt.Println("Benefits: Flexibility, testability, extensibility, loose coupling")
}
