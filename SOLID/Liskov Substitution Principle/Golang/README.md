# Liskov Substitution Principle (LSP) - Go Example

This example demonstrates the Liskov Substitution Principle in Go, showing how to design interfaces that allow proper substitution of subtypes.

## What is LSP?

The Liskov Substitution Principle states that subtypes must be substitutable for their base types without altering the correctness of the program. In other words, if you have a base type, any derived type should be able to replace it without breaking the program.

## Running the Example

```bash
# Navigate to the Golang directory
cd "Liskov Substitution Principle/Golang"

# Run the example
go run main.go
```

## Example Output

The program demonstrates:

1. **LSP Violation**: `Ostrich` inherits from `Bird` but can't fly, causing runtime panics
2. **LSP Compliance**: Separate interfaces for different bird capabilities
3. **Proper Substitution**: Type-safe substitution using interfaces

## Key Concepts Demonstrated

### LSP Violation

- `Bird` struct with `fly()` method
- `Ostrich` embeds `Bird` but overrides `fly()` to panic
- This breaks the substitution principle

### LSP Compliance

- `BirdInterface` for basic bird behavior (eating)
- `FlyingBird` interface extends `BirdInterface` for flying birds
- `Sparrow` implements `FlyingBird` (can eat and fly)
- `OstrichLSP` implements only `BirdInterface` (can eat but not fly)

## Benefits of Following LSP

- **Type Safety**: Compile-time checking prevents invalid substitutions
- **Runtime Safety**: No unexpected panics or errors
- **Maintainability**: Clear contracts about what each type can do
- **Extensibility**: Easy to add new bird types with appropriate capabilities
- **Polymorphism**: Functions can work with any type that implements the required interface
