# Factory Design Pattern

## Definition
The Factory Design Pattern is a creational design pattern that provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created. It encapsulates object creation logic and provides a way to create objects without specifying their exact classes.

## Purpose
- **Encapsulation of Object Creation**: Hides the complexity of object instantiation from client code
- **Loose Coupling**: Reduces dependency between client code and concrete classes
- **Centralized Creation Logic**: Manages object creation in a single location for easier maintenance
- **Extensibility**: Easy to add new product types without modifying existing client code
- **Code Reusability**: Factory methods can be reused across different parts of the application

## Where to Use
- **UI Component Creation**: Creating different types of buttons, dialogs, or widgets based on platform
- **Database Connection Management**: Creating different database connection objects (MySQL, PostgreSQL, MongoDB)
- **Game Development**: Creating different types of enemies, weapons, or characters
- **Logging Systems**: Creating different logger implementations (File, Console, Database)
- **Vehicle Manufacturing**: Creating different types of vehicles (Car, Truck, Motorcycle)

## Implementation Structure

### Components
1. **Product Interface** (`Shape`): Common interface for all products created by the factory
2. **Concrete Products** (`Circle`, `Square`): Specific implementations of the product interface
3. **Factory Class** (`ShapeFactory`): Contains the creation logic and returns appropriate product instances
4. **Client** (`Main`): Uses the factory to create objects without knowing their concrete types

### Class Diagram
```
Shape (Product Interface)
    ↑
    ├── Circle (ConcreteProduct)
    └── Square (ConcreteProduct)

ShapeFactory (Factory)
    └── getShape(type: String): Shape

Client → ShapeFactory → Shape
```

## Benefits
- **Encapsulation**: Object creation logic is centralized and hidden
- **Flexibility**: Easy to modify creation logic without affecting client code
- **Testability**: Factory methods can be easily mocked for unit testing
- **Maintainability**: Changes to object creation only require factory modifications
- **Polymorphism**: Clients work with interfaces rather than concrete classes

## Drawbacks
- **Increased Complexity**: Additional layer of abstraction may be overkill for simple cases
- **Code Overhead**: More classes and interfaces to maintain
- **Runtime Dependencies**: Object type determined at runtime, not compile time
- **Parameter Dependency**: Factory methods often rely on string parameters or enums

## Best Practices
- Use meaningful names for factory methods (e.g., `createShape()` instead of `getShape()`)
- Consider using enums instead of strings for type parameters
- Implement proper error handling for unknown types
- Keep factory methods focused on creation, not business logic
- Consider using dependency injection frameworks for complex scenarios

## Real-world Applications
- **Java Collections**: `Collections.synchronizedList()`, `Arrays.asList()`
- **JDBC**: `DriverManager.getConnection()` creates database connections
- **Spring Framework**: Bean factories create and manage object instances
- **Android Development**: `LayoutInflater` creates UI components from XML
- **Web Development**: HTTP client factories for different protocols (REST, GraphQL, SOAP)
