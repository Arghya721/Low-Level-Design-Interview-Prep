// ===== VIOLATES DIP =====
// LightBulb is a concrete low-level module
class LightBulb {
    private String name;
    
    public LightBulb(String name) {
        this.name = name;
    }
    
    public void turnOn() {
        System.out.println(name + " light bulb is turned on");
    }
    
    public void turnOff() {
        System.out.println(name + " light bulb is turned off");
    }
}

// Switch depends directly on LightBulb (high-level depends on low-level)
class Switch {
    private LightBulb bulb;
    
    public Switch() {
        this.bulb = new LightBulb("Living Room");
    }
    
    public void operate() {
        bulb.turnOn();
    }
}

// ===== FOLLOWS DIP =====
// Switchable is an abstraction (interface) that both high and low level modules depend on
interface Switchable {
    void turnOn();
    void turnOff();
}

// LightBulbDIP implements Switchable interface
class LightBulbDIP implements Switchable {
    private String name;
    
    public LightBulbDIP(String name) {
        this.name = name;
    }
    
    @Override
    public void turnOn() {
        System.out.println(name + " light bulb is turned on");
    }
    
    @Override
    public void turnOff() {
        System.out.println(name + " light bulb is turned off");
    }
}

// Fan implements Switchable interface
class Fan implements Switchable {
    private String name;
    
    public Fan(String name) {
        this.name = name;
    }
    
    @Override
    public void turnOn() {
        System.out.println(name + " fan is turned on");
    }
    
    @Override
    public void turnOff() {
        System.out.println(name + " fan is turned off");
    }
}

// TV implements Switchable interface
class TV implements Switchable {
    private String name;
    
    public TV(String name) {
        this.name = name;
    }
    
    @Override
    public void turnOn() {
        System.out.println(name + " TV is turned on");
    }
    
    @Override
    public void turnOff() {
        System.out.println(name + " TV is turned off");
    }
}

// SwitchDIP depends on abstraction (Switchable interface), not concrete implementation
class SwitchDIP {
    private Switchable device;
    
    public SwitchDIP(Switchable device) {
        this.device = device;
    }
    
    public void operate() {
        device.turnOn();
    }
    
    public void turnOff() {
        device.turnOff();
    }
}

// MockDevice for testing (implements Switchable interface)
class MockDevice implements Switchable {
    private String name;
    
    public MockDevice(String name) {
        this.name = name;
    }
    
    @Override
    public void turnOn() {
        System.out.println("MOCK: " + name + " is turned on (for testing)");
    }
    
    @Override
    public void turnOff() {
        System.out.println("MOCK: " + name + " is turned off (for testing)");
    }
}

// ===== DEMONSTRATION CLASS =====
public class Switch {
    
    public static void demonstrateDIPViolation() {
        System.out.println("\n=== DEMONSTRATING DIP VIOLATION ===");
        
        // Switch is tightly coupled to LightBulb
        Switch sw = new Switch();
        System.out.println("Switch operating (can only control LightBulb):");
        sw.operate();
        
        System.out.println("\nProblem: Switch can only work with LightBulb, not other devices");
    }
    
    public static void demonstrateDIPCompliance() {
        System.out.println("\n=== DEMONSTRATING DIP COMPLIANCE ===");
        
        // Create different devices
        Switchable lightBulb = new LightBulbDIP("Kitchen");
        Switchable fan = new Fan("Bedroom");
        Switchable tv = new TV("Living Room");
        
        // Create switches that can control any switchable device
        SwitchDIP lightSwitch = new SwitchDIP(lightBulb);
        SwitchDIP fanSwitch = new SwitchDIP(fan);
        SwitchDIP tvSwitch = new SwitchDIP(tv);
        
        // All switches can operate any device
        System.out.println("Operating different devices:");
        lightSwitch.operate();
        fanSwitch.operate();
        tvSwitch.operate();
        
        System.out.println("\nTurning off devices:");
        lightSwitch.turnOff();
        fanSwitch.turnOff();
        tvSwitch.turnOff();
    }
    
    public static void demonstrateFlexibility() {
        System.out.println("\n=== DEMONSTRATING FLEXIBILITY ===");
        
        // Create an array of different devices
        Switchable[] devices = {
            new LightBulbDIP("Hallway"),
            new Fan("Office"),
            new TV("Den"),
            new LightBulbDIP("Garage")
        };
        
        // Create switches for each device
        SwitchDIP[] switches = new SwitchDIP[devices.length];
        for (int i = 0; i < devices.length; i++) {
            switches[i] = new SwitchDIP(devices[i]);
        }
        
        // Operate all devices
        System.out.println("Operating all devices:");
        for (int i = 0; i < switches.length; i++) {
            System.out.print("Switch " + (i + 1) + ": ");
            switches[i].operate();
        }
        
        // Turn off all devices
        System.out.println("\nTurning off all devices:");
        for (int i = 0; i < switches.length; i++) {
            System.out.print("Switch " + (i + 1) + ": ");
            switches[i].turnOff();
        }
    }
    
    public static void demonstrateTesting() {
        System.out.println("\n=== DEMONSTRATING TESTING BENEFITS ===");
        
        // Mock device for testing
        Switchable mockDevice = new MockDevice("Test Device");
        SwitchDIP testSwitch = new SwitchDIP(mockDevice);
        
        System.out.println("Testing switch with mock device:");
        testSwitch.operate();
        testSwitch.turnOff();
    }
    
    public static void main(String[] args) {
        System.out.println("Dependency Inversion Principle (DIP) Example");
        System.out.println("===========================================");
        
        // Demonstrate DIP violation
        demonstrateDIPViolation();
        
        // Demonstrate DIP compliance
        demonstrateDIPCompliance();
        
        // Demonstrate flexibility
        demonstrateFlexibility();
        
        // Demonstrate testing benefits
        demonstrateTesting();
        
        System.out.println("\n=== SUMMARY ===");
        System.out.println("DIP Violation: High-level module (Switch) depends directly on low-level module (LightBulb)");
        System.out.println("DIP Compliance: Both high and low level modules depend on abstraction (Switchable interface)");
        System.out.println("Benefits: Flexibility, testability, extensibility, loose coupling");
    }
} 