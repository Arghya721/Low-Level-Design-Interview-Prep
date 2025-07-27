# Strategy Design Pattern

## Definition
The Strategy Pattern is a behavioral design pattern that enables the selection of an algorithm or behavior at runtime. It defines a family of algorithms, encapsulates each one, and makes them interchangeable. This pattern allows the algorithm to vary independently from clients that use it.

## Purpose
- **Runtime Algorithm Selection**: Choose different algorithms or behaviors during program execution
- **Code Flexibility**: Easily switch between different implementations without modifying client code
- **Encapsulation**: Each algorithm is encapsulated in its own class, promoting clean code organization
- **Open/Closed Principle**: Open for extension (new strategies) but closed for modification (existing code)

## Where to Use
- **Payment Processing**: Different payment methods (Credit Card, UPI, PayPal, etc.)
- **Sorting Algorithms**: Various sorting strategies (QuickSort, MergeSort, BubbleSort)
- **Compression**: Different compression algorithms (ZIP, RAR, GZIP)
- **Discount Calculations**: Various discount strategies for e-commerce applications
- **Data Validation**: Different validation rules for form inputs
- **File Export**: Multiple export formats (PDF, Excel, CSV)

## Implementation Structure

### Components
1. **Strategy Interface** (`PayInterface`): Defines common interface for all concrete strategies
2. **Concrete Strategies** (`CreditCardPayment`, `UPIPayment`): Implement specific algorithms
3. **Context** (`Payments`): Maintains reference to strategy object and delegates work
4. **Client** (`Main`): Creates and configures strategy objects

### Class Diagram
```
PayInterface (Strategy)
    ↑
    ├── CreditCardPayment (ConcreteStrategy)
    └── UPIPayment (ConcreteStrategy)

Payments (Context) → PayInterface
```

## Benefits
- **Eliminates conditional statements** for algorithm selection
- **Makes algorithms reusable** across different contexts
- **Easier testing** as each strategy can be tested independently
- **Runtime flexibility** to change behavior dynamically
- **Follows SOLID principles** especially Open/Closed and Single Responsibility

## Real-world Applications
- **E-commerce platforms**: Payment gateways, shipping methods, pricing strategies
- **Gaming**: AI behavior, character movement patterns, combat strategies
- **Data Processing**: File parsing, data transformation algorithms
- **UI Components**: Rendering strategies, animation effects
