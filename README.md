# Robot Cleaner Installation Guide

This guide provides instructions for installing and running the Robot Cleaner Spring Boot application from https://github.com/dmanias/robot-cleaner.git.

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6 or higher

## Installation Steps (for Windows, Linux, and macOS)

1. Clone the repository:
```
git clone https://github.com/dmanias/robot-cleaner.git
cd robot-cleaner
```

2. Build the application:
```
mvn clean install
```

3. Run the application:
```
mvn spring-boot:run
```

## Usage

Once the application is running, you can access the API endpoints using a tool like cURL, Postman, or your web browser.

### API Documentation

The API documentation is available via Swagger UI. You can access it at:

```
http://localhost:8080/swagger-ui/index.html#/Hoover/navigateRoom
```

Use this interface to explore available endpoints, test API calls, and understand request/response structures.

### Main Endpoint

The main endpoint for cleaning instructions is:

```
POST http://localhost:8080/api/hoover/navigate
```

You need to send a JSON payload with the room dimensions, starting position, and cleaning instructions. For example:

```json
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```

The application will return the final position of the robot and the number of cleaned patches.

## Testing

This project includes both unit tests and integration tests, as well as a shell script for API testing.

To run the unit and integration tests, use the following Maven command:

```
mvn test
```
This will execute all the tests in the project and provide a summary of the results.

For API testing, a shell script is provided. To run these tests:

1. Ensure your Spring Boot application is running on `localhost:8080`.
2. Open a terminal and navigate to the project root.
3. Run the following command:

   ./tests/api_test_suite.sh

This will execute a series of curl commands to test various scenarios including valid inputs, invalid room sizes, invalid patch positions, and empty instructions.

Note: You may need to make the script executable first by running `chmod +x tests/api_test_suite.sh`.

## Additional Notes

- Make sure you have the correct Java version installed. This project requires Java 17.
- If you encounter any issues, check the project's README.md file for additional information or requirements.
- For development purposes, you can use the embedded H2 database. For production, you might want to configure a different database in the application.properties file.

## Technology Stack

This project demonstrates best practices in Spring Boot development, focusing on:

- RESTful API design
- OpenAPI (Swagger) for API documentation
- Layered architecture for better separation of concerns
- Comprehensive testing with JUnit and Mockito
- Logging with SLF4J and Logback
- Basic security measures including:
    - Input validation
    - CORS configuration
    - Rate limiting

For more details on the architectural decisions, refer to the project documentation.

## Contributing

Contributions to the Robot Cleaner project are welcome. Please refer to the project's GitHub repository for information on how to contribute.

## License

MIT License
Copyright (c) 2024 Dimosthenis Manias  
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:  
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.  
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.