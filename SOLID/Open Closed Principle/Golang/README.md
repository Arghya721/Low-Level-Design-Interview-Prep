# Open/Closed Principle (OCP) - Go Example

This example demonstrates the Open/Closed Principle in Go, showing how to design code that is open for extension but closed for modification.

## What is OCP?

The Open/Closed Principle states that software entities (classes, modules, functions) should be open for extension but closed for modification. This means you should be able to add new functionality without changing existing code.

## Running the Example

```bash
# Navigate to the Golang directory
cd "Open Closed Principle/Golang"

# Run the example
go run main.go
```

## Example Output

```
Paid using Credit Card
Paid using PayPal
```

## How OCP is Applied

1. **Interface Definition**: `PaymentMethod` interface defines the contract
2. **Multiple Implementations**: `CreditCard` and `PayPal` implement the interface
3. **Extensible Design**: New payment methods can be added without modifying existing code
4. **Closed for Modification**: `ProcessPayment` function doesn't need to change when new payment methods are added

## Benefits

- **Extensibility**: Easy to add new payment methods
- **Maintainability**: Existing code remains unchanged
- **Testability**: Each payment method can be tested independently
- **Flexibility**: Payment processing logic is decoupled from specific implementations
