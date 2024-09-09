# Spring Boot App Installation Guide

This guide provides instructions for installing and running a Spring Boot application on Linux and macOS systems.

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven (for building the application)

## Linux Installation

1. Install Java:
   ```bash
   sudo apt update
   sudo apt install default-jdk
   ```

2. Install Maven:
   ```bash
   sudo apt install maven
   ```

3. Clone your Spring Boot application repository:
   ```bash
   git clone https://github.com/dmanias/robot-cleaner.git
   cd <your-app-directory>
   ```

4. Build the application:
   ```bash
   mvn clean package
   ```

5. Run the application:
   ```bash
   java -jar target/<your-app-name>.jar
   ```

## macOS Installation

1. Install Homebrew (if not already installed):
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```

2. Install Java:
   ```bash
   brew install openjdk
   ```

3. Install Maven:
   ```bash
   brew install maven
   ```

4. Clone your Spring Boot application repository:
   ```bash
   git clone <your-repo-url>
   cd <your-app-directory>
   ```

5. Build the application:
   ```bash
   mvn clean package
   ```

6. Run the application:
   ```bash
   java -jar target/<your-app-name>.jar
   ```

## Additional Notes

- Replace `<your-repo-url>` with the actual URL of your Git repository.
- Replace `<your-app-directory>` with the name of your application's directory.
- Replace `<your-app-name>.jar` with the actual name of your compiled JAR file.

To run the application as a background service, consider using systemd on Linux or launchd on macOS.