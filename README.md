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

## Additional Notes

- Make sure you have the correct Java version installed. This project requires Java 17.
- If you encounter any issues, check the project's README.md file for additional information or requirements.
- For development purposes, you can use the embedded H2 database. For production, you might want to configure a different database in the application.properties file.