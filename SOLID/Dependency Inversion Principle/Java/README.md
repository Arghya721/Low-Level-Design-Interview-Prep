# Dependency Inversion Principle (DIP) - Java Example

This example demonstrates the Dependency Inversion Principle in Java, showing how to design systems where high-level modules depend on abstractions rather than low-level modules.

## What is DIP?

The Dependency Inversion Principle states that:

1. High-level modules should not depend on low-level modules. Both should depend on abstractions.
2. Abstractions should not depend on details. Details should depend on abstractions.

## Running the Example

```bash
# Navigate to the Java directory
cd "Dependency Inversion Principle/Java"

# Compile and run
javac Switch.java
java Switch
```

## Example Output

The program demonstrates:

1. **DIP Violation**: `Switch` depends directly on `LightBulb` (high-level depends on low-level)
2. **DIP Compliance**: Both `Switch` and devices depend on `Switchable` interface (abstraction)
3. **Flexibility**: Switch can control any device that implements the interface
4. **Testing**: Easy to test with mock devices

## Key Concepts Demonstrated

### DIP Violation

- `Switch` class depends directly on `LightBulb` class
- High-level module (Switch) depends on low-level module (LightBulb)
- Tight coupling makes the system inflexible

### DIP Compliance

- `Switchable` interface serves as abstraction
- Both `Switch` and device implementations depend on the interface
- Loose coupling allows for flexibility and extensibility

## Benefits of Following DIP

- **Flexibility**: Switch can control any device implementing the interface
- **Testability**: Easy to test with mock devices
- **Extensibility**: New devices can be added without modifying existing code
- **Loose Coupling**: High-level modules don't depend on low-level details
- **Maintainability**: Changes to device implementations don't affect the switch

## Java-Specific Features Used

- **Interface-based Design**: Abstractions defined through interfaces
- **Dependency Injection**: Dependencies passed through constructors
- **Polymorphism**: Methods work with any type implementing the interface
- **Method Overriding**: Proper implementation of interface methods
- **Mock Objects**: Easy creation of test doubles
