# Hello Spring Boot

A simple Spring Boot application with Java 21 that provides a GET endpoint returning "Hello".

## Requirements

- Java 21
- Maven 3.6+

## Running the Application

```bash
./mvnw spring-boot:run
```

Or if you're on Windows:

```bash
mvnw.cmd spring-boot:run
```

## Testing the Endpoint

Once the application is running, you can test the endpoint:

```bash
curl http://localhost:8080/hello
```

Or open your browser and navigate to: `http://localhost:8080/hello`

Expected response: `Hello`

## Project Structure

```
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── hello
│       │               ├── HelloApplication.java
│       │               └── HelloController.java
│       └── resources
│           └── application.properties
```

## Technologies

- **Spring Boot**: 3.2.0
- **Java**: 21
- **Maven**: Build tool
# test-java
