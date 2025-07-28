# Builder Design Pattern

## Definition
The Builder Design Pattern is a creational design pattern that is used to construct a complex object step-by-step. It separates the construction of a complex object from its representation, allowing the same construction process to create different representations. This pattern is particularly useful when creating objects with many optional parameters or when the construction process is complex.

## Purpose
- **Step-by-Step Construction**: Builds complex objects incrementally through a series of method calls
- **Separation of Concerns**: Separates object construction logic from its representation
- **Parameter Validation**: Allows validation of parameters during the building process
- **Immutable Objects**: Creates immutable objects by controlling the construction process
- **Flexible Object Creation**: Enables different representations of the same object type

## Where to Use
- **Configuration Objects**: Building complex configuration objects with many optional parameters
- **Database Query Builders**: Constructing SQL queries with optional clauses (WHERE, ORDER BY, GROUP BY)
- **HTTP Request Builders**: Creating HTTP requests with optional headers, parameters, and body
- **User Profile Creation**: Building user objects with mandatory and optional fields
- **Document Generation**: Creating documents with various optional sections and formatting
- **Game Character Creation**: Building game characters with different attributes and abilities
- **UI Component Building**: Creating complex UI components with various styling options

## Implementation Structure

### Components
1. **Product** (`Student`/`User`): The complex object being constructed
2. **Builder** (`StudentBuilder`/`UserBuilder`): Provides methods to set individual parts of the product
3. **Director** (Optional): Controls the construction process (not shown in our examples)
4. **Client** (`Main`): Uses the builder to construct the product

### Class Diagram
```
Student (Product)
    ← constructed by
StudentBuilder (Builder)
    ├── + StudentBuilder(firstName, lastName)
    ├── + age(int): StudentBuilder
    ├── + email(String): StudentBuilder
    ├── + phoneNumber(String): StudentBuilder
    └── + build(): Student

Client → StudentBuilder → Student
```

## Benefits
- **Readable Code**: Method chaining creates self-documenting code
- **Parameter Validation**: Can validate parameters during building process
- **Immutable Objects**: Creates immutable objects after construction
- **Optional Parameters**: Handles objects with many optional parameters elegantly
- **Consistent Object State**: Ensures objects are properly constructed before use

## Drawbacks
- **Code Verbosity**: Requires more code compared to simple constructors
- **Memory Overhead**: Builder objects consume additional memory
- **Complexity**: Overkill for simple objects with few parameters
- **Learning Curve**: More complex pattern requiring understanding of method chaining

## Best Practices
- Use for objects with 4+ parameters or complex construction logic
- Make required parameters part of builder constructor
- Return builder instance from each setter method for chaining
- Include validation logic in build() method
- Consider making the product class immutable
- Use meaningful method names that describe what's being set

## Real-world Applications
- **StringBuilder/StringBuffer**: Java's string building utilities
- **Retrofit**: HTTP client library for Android with builder pattern
- **Apache HttpClient**: HTTP request builders
- **JDBC PreparedStatement**: SQL query building
- **Spring Boot**: Configuration builders and auto-configuration
- **Docker**: Dockerfile instructions build images step-by-step
- **Maven/Gradle**: Build tool configuration with incremental steps
