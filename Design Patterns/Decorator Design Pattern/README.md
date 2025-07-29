# Decorator Design Pattern

## Definition
The Decorator Design Pattern is a structural design pattern that is used to add behavior to an object dynamically without affecting other objects from the same class. It's typically used to add responsibilities to individual objects, not to an entire class. The pattern allows you to wrap objects in a series of decorator classes that contain the behaviors, providing a flexible alternative to subclassing for extending functionality.

## Purpose
- **Dynamic Behavior Addition**: Add new functionality to objects at runtime without altering their structure
- **Flexible Extension**: Extend object behavior without modifying the original class or creating multiple subclasses
- **Composition over Inheritance**: Use object composition instead of inheritance to achieve behavior extension
- **Single Responsibility**: Each decorator handles a specific aspect of functionality
- **Open/Closed Principle**: Open for extension but closed for modification

## Where to Use
- **Text Processing**: Adding formatting (bold, italic, underline) to text objects
- **Pizza/Food Ordering**: Adding toppings, sides, or extras to base food items
- **Stream Processing**: Adding compression, encryption, or buffering to data streams
- **Web Development**: Adding middleware layers (authentication, logging, caching) to HTTP requests
- **Game Development**: Adding power-ups, abilities, or equipment to game characters

## Implementation Structure

### Components
1. **Component Interface** (`BasePizzaInterface`): Common interface for both concrete components and decorators
2. **Concrete Component** (`Margherita`, `FarmHouse`): Base objects that can be decorated
3. **Decorator Interface** (`ToppingInterface`): Extends the component interface and contains a reference to a component
4. **Concrete Decorators** (`Mushroom`, `CheeseBurst`): Add specific behaviors to the component
5. **Client** (`Main`): Uses decorated objects through the component interface

### Class Diagram
```
BasePizzaInterface (Component)
    ↑
    ├── Margherita (ConcreteComponent)
    ├── FarmHouse (ConcreteComponent)
    └── ToppingInterface (Decorator)
        ↑
        ├── Mushroom (ConcreteDecorator)
        └── CheeseBurst (ConcreteDecorator)

Client → BasePizzaInterface ← wraps ← ConcreteDecorator
```

## Benefits
- **Runtime Flexibility**: Add or remove behaviors dynamically during execution
- **Single Responsibility**: Each decorator has one specific purpose
- **Composability**: Multiple decorators can be combined in any order
- **Alternative to Inheritance**: Avoids class explosion from multiple inheritance paths
- **Transparent Interface**: Decorated objects maintain the same interface as base objects

## Drawbacks
- **Complexity**: Can create many small objects and complex object graphs
- **Debugging Difficulty**: Nested decorators can be hard to debug and trace
- **Performance Overhead**: Multiple method calls through decoration layers
- **Order Dependency**: Some decorators may be sensitive to the order of application
- **Identity Issues**: Decorated objects lose their original identity

## Best Practices
- Keep decorators lightweight and focused on single responsibilities
- Ensure decorators maintain the same interface as the component
- Design decorators to be order-independent when possible
- Use meaningful names that clearly indicate the decoration being applied
- Consider providing factory methods for common decorator combinations
- Document any order dependencies between decorators

## Real-world Applications
- **Java I/O Streams**: BufferedReader wrapping FileReader for buffered reading
- **Spring Framework**: Method interceptors and AOP proxies
- **Web Development**: Middleware layers in Express.js, Django
- **GUI Frameworks**: Swing/AWT components with borders, scrollbars
- **Logging Frameworks**: Log4j appenders and layouts
- **HTTP Clients**: Request/response interceptors in Retrofit, OkHttp
- **Game Development**: Character abilities, weapon modifications, armor enhancements
