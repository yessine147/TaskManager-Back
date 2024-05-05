# Task Manager Application

This is a simple Task Manager application built using Spring Boot. It allows users to perform CRUD operations on tasks.

## Prerequisites

| name   | link                                                                                                |
|--------|-----------------------------------------------------------------------------------------------------|
| JDK 17 | [link download JDK17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) |
| MAVEN  | [link download maven](https://maven.apache.org/download.cgi)                                        |

## Pom Dependency

- Spring Web: Used for building web applications or RESTful APIs with Spring MVC.
- Spring Data JPA: Simplifies data access layer development by providing a high-level abstraction over JPA.
- H2 Database: Lightweight in-memory SQL database commonly used for development and testing.
- Lombok: Reduces boilerplate code by automatically generating getters, setters, constructors, etc.
- devtools: Enhances development experience with features like automatic application restarts and live reloading.

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.backend.taskmanager` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html):

```shell
mvn spring-boot:run
```

The application should now be running. You can access it at [http://localhost:3000](http://localhost:3000).

You can access your H2 database at [http://localhost:3000/h2-console](http://localhost:3000/h2-console).

## Usage

- Use a tool like Postman or curl to interact with the API endpoints.( you can use my postman collection from [here](https://www.postman.com/yessinebj/workspace/task-manager/collection/13471337-4e21325e-05db-4a49-8a19-518609d98e49?action=share&creator=13471337))
- Refer to the API documentation in the controller classes for details on available endpoints and request/response formats.
