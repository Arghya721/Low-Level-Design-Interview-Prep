# Open/Closed Principle (OCP) - Java Example

This example demonstrates the Open/Closed Principle in Java, showing how to design code that is open for extension but closed for modification.

## What is OCP?

The Open/Closed Principle states that software entities (classes, modules, functions) should be open for extension but closed for modification. This means you should be able to add new functionality without changing existing code.

## Running the Example

```bash
# Navigate to the Java directory
cd "Open Closed Principle/Java"

# Compile and run
javac PaymentMethod.java
java PaymentMethod
```

## Example Output

```
Open/Closed Principle (OCP) Example
===================================
Processing payments:
Paid using Credit Card
Paid using PayPal
Paid using Bank Transfer
Paid using Cryptocurrency

=== SUMMARY ===
OCP Compliance: New payment methods can be added without modifying existing code
Benefits: Extensibility, maintainability, and flexibility
```

## How OCP is Applied

1. **Interface Definition**: `PaymentMethod` interface defines the contract
2. **Multiple Implementations**: `CreditCard`, `PayPal`, `BankTransfer`, and `CryptoPayment` implement the interface
3. **Extensible Design**: New payment methods can be added without modifying existing code
4. **Closed for Modification**: `ProcessPayment` class doesn't need to change when new payment methods are added

## Benefits

- **Extensibility**: Easy to add new payment methods
- **Maintainability**: Existing code remains unchanged
- **Testability**: Each payment method can be tested independently
- **Flexibility**: Payment processing logic is decoupled from specific implementations

## Java-Specific Features Used

- **Interfaces**: Define contracts for payment methods
- **Polymorphism**: ProcessPayment works with any PaymentMethod implementation
- **Inheritance**: Each payment method implements the same interface
- **Encapsulation**: Payment logic is encapsulated within each implementation
