# Interface Segregation Principle (ISP) - Go Example

This example demonstrates the Interface Segregation Principle in Go, showing how to design focused interfaces that don't force unnecessary implementations.

## What is ISP?

The Interface Segregation Principle states that clients should not be forced to depend on interfaces they do not use. It's better to have many small, specific interfaces than one large general-purpose interface.

## Running the Example

```bash
# Navigate to the Golang directory
cd "Interface Segregation Principle/Golang"

# Run the example
go run main.go
```

## Example Output

The program demonstrates:

1. **ISP Violation**: `Worker` interface forces all implementations to have both `work()` and `eat()` methods
2. **ISP Compliance**: Separate interfaces (`Workable`, `Eatable`) for different capabilities
3. **Interface Composition**: Shows how to use multiple focused interfaces

## Key Concepts Demonstrated

### ISP Violation

- `Worker` interface with both `work()` and `eat()` methods
- `RobotWorker` forced to implement `eat()` even though robots don't eat
- Violates the principle by forcing unnecessary implementations

### ISP Compliance

- `Workable` interface for entities that can work
- `Eatable` interface for entities that can eat
- `HumanWorkerISP` implements both interfaces
- `RobotWorkerISP` implements only `Workable`

## Benefits of Following ISP

- **No Forced Implementations**: Types only implement methods they actually need
- **Better Separation of Concerns**: Each interface has a single, clear purpose
- **More Flexible Design**: Functions can accept only the capabilities they need
- **Easier Testing**: Smaller interfaces are easier to mock and test
- **Improved Maintainability**: Changes to one capability don't affect others

## Go-Specific Features Used

- **Interface Composition**: Types can implement multiple interfaces
- **Interface Segregation**: Small, focused interfaces
- **Polymorphism**: Functions work with any type implementing the required interface
