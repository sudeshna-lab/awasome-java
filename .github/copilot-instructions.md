# Copilot Instructions

# Copilot Instructions for Modern Java 21+ Spring Boot Application

## Overview
This document provides comprehensive instructions on how to utilize GitHub Copilot effectively while developing a Java 21+ Spring Boot application following enterprise coding standards, best practices, and architectural patterns.

## Prerequisites
- **Java Version**: Ensure you are using Java 21 or higher.
- **Spring Boot**: Use the latest version of Spring Boot compatible with Java 21.
- **IDE**: Visual Studio Code or IntelliJ IDEA with GitHub Copilot enabled.

## Setting Up Your Environment
1. Install Java 21 SDK on your machine.
2. Setup a Spring Boot project using Spring Initializr or your IDE’s project setup features.
3. Enable GitHub Copilot in your IDE:
   - **Visual Studio Code**: Go to Extensions and enable GitHub Copilot.
   - **IntelliJ IDEA**: Go to File > Settings > Plugins and enable GitHub Copilot.

## Writing Code with Copilot
### Using Context Effectively
- **Code Comments**: Write clear and concise comments. Copilot uses these to suggest code snippets. For instance:
  ```java
  // Fetch all users from the database
  List<User> users = userService.getAllUsers();
  ```

### Functions and Classes
- Structure your classes following SOLID principles.
- Utilize Copilot to generate boilerplate code for classes and methods. For example:
  ```java
  public class UserService {
      // Generate methods with comments
  }
  ```

### Exception Handling
- Always handle exceptions gracefully. Use comments to instruct Copilot to generate structured exception handling.
  ```java
  // Handle any exceptions that may occur while fetching users
  try {
      List<User> users = userService.getAllUsers();
  } catch (Exception e) {
      // Log error
  }
  ```

### Testing with JUnit
- Encourage Copilot to generate unit tests by providing proper annotations and comments.
  ```java
  // Test UserService methods
  @Test
  public void testGetAllUsers() {
      // Expecting a list of users
  }
  ```

## Architectural Patterns
- **Layered Architecture**: Enforce a layered architecture (Controller, Service, Repository) and guide Copilot with clear comments.
- **Microservices**: For microservices architecture, specify the context and let Copilot suggest relevant service interfaces and implementations.
- **Event-Driven Architecture**: If employing this pattern, instruct Copilot to help create event listeners and publishers.

## Code Reviews
- Review generated code for efficiency and clarity. Ensure it adheres to coding standards and patterns followed in your enterprise.

## Best Practices
- Document every significant change made by Copilot.
- Keep dependencies updated to the latest versions for security and stability.

## Conclusion
By following these guidelines and making the most out of GitHub Copilot, you can enhance your development experience while adhering to modern Java enterprise standards.
