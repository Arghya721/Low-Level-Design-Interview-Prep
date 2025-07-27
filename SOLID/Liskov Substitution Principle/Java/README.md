# Liskov Substitution Principle (LSP) - Java Example

This example demonstrates the Liskov Substitution Principle in Java, showing how to design interfaces that allow proper substitution of subtypes.

## What is LSP?

The Liskov Substitution Principle states that subtypes must be substitutable for their base types without altering the correctness of the program. In other words, if you have a base type, any derived type should be able to replace it without breaking the program.

## Running the Example

```bash
# Navigate to the Java directory
cd "Liskov Substitution Principle/Java"

# Compile and run
javac Bird.java
java Bird
```

## Example Output

The program demonstrates:

1. **LSP Violation**: `Ostrich` extends `Bird` but can't fly, causing runtime exceptions
2. **LSP Compliance**: Separate interfaces for different bird capabilities
3. **Proper Substitution**: Type-safe substitution using interfaces

## Key Concepts Demonstrated

### LSP Violation

- `Bird` class with `fly()` method
- `Ostrich` extends `Bird` but overrides `fly()` to throw exception
- This breaks the substitution principle

### LSP Compliance

- `BirdInterface` for basic bird behavior (eating)
- `FlyingBird` interface extends `BirdInterface` for flying birds
- `Sparrow` implements `FlyingBird` (can eat and fly)
- `OstrichLSP` implements only `BirdInterface` (can eat but not fly)

## Benefits of Following LSP

- **Type Safety**: Compile-time checking prevents invalid substitutions
- **Runtime Safety**: No unexpected exceptions or errors
- **Maintainability**: Clear contracts about what each type can do
- **Extensibility**: Easy to add new bird types with appropriate capabilities
- **Polymorphism**: Methods can work with any type that implements the required interface

## Java-Specific Features Used

- **Interfaces**: Define contracts for different bird capabilities
- **Inheritance**: Shows both problematic inheritance and proper interface implementation
- **Exception Handling**: Demonstrates runtime errors from LSP violations
- **Polymorphism**: Functions accept interface types for flexibility
