# Robot Cleaner API - Architectural Decisions

This project implements a RESTful API for controlling a robot cleaner, showcasing modern Spring Boot development practices and advanced architectural concepts.

## Technologies and Practices

### Core Framework

* **Spring Boot**: Leveraging the power of Spring Boot for rapid development and easy configuration.

### API Design and Documentation

* **RESTful API**: Implementing a clean, resource-oriented API design.
* **OpenAPI (Swagger)**: Automatic API documentation and interactive testing interface.
* **DTOs (Data Transfer Objects)**: Separating API models from internal domain models for flexibility and security.

### Modular Architecture

* **Layered Architecture**: Implementing a ports and adapters pattern for better separation of concerns and testability.
* **Dependency Injection**: Utilizing Spring's DI for loose coupling and easier testing.

### Data Validation and Error Handling

* **Input Validation**: Using Spring's validation annotations for robust input sanitization.
* **Global Exception Handling**: Implementing @ControllerAdvice for consistent error responses across the API.
* **Custom Exception Hierarchy**: Creating a structured approach to exception management.

### Testing

* **JUnit and Mockito**: Comprehensive unit testing of services and controllers.
* **Integration Testing**: Ensuring components work together as expected.

### Logging and Monitoring

* **SLF4J and Logback**: Configurable logging across different environments.
* **AOP (Aspect-Oriented Programming)**: Centralized logging of method calls and exceptions.

### Security (Without Authentication)

* **Input Validation**: Strict validation to prevent injection attacks.
* **CORS Configuration**: Restricting API access to trusted domains.
* **Rate Limiting**: Preventing API abuse with request rate limits.

Note: While the current implementation includes these basic security measures, additional security features such as comprehensive security headers, HTTPS enforcement, and regular dependency vulnerability scanning are recommended for production environments.

### Advanced Concepts

* **Profiles**: Environment-specific configurations for development, testing, and production.
* **Conditional Beans**: Flexible service implementations based on the current environment.

## Conclusion

This project serves as a demonstration of best practices in Spring Boot development, focusing on modularity, testability, and basic security measures. It provides a foundation for scalable and maintainable microservices architecture, with the potential for further enhancements in security and other areas as needed for production environments.