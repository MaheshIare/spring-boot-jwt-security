# Spring Boot JWT Security Demo

## About
This is a simple demo for show casing the usage of **[JWT (JSON Web Token)](https://jwt.io)** with **[Spring Security](https://spring.io/projects/spring-security)** and
**[Spring Boot](https://spring.io/projects/spring-boot)**.

## Requirements
This demo is build with with Spring boot 2.4.4.

## Usage
Just start the application with the Spring Boot maven plugin (`mvn spring-boot:run`). The application is
running at [Localhost](http://localhost:8080/spring-boot-jwt-security).

You can use the **H2-Console** for exploring the database under [H2-Console](http://localhost:8080/spring-boot-jwt-security/h2-console):


## Backend
There are three user accounts present to demonstrate the different levels of access to the endpoints in
the API and the different authorization exceptions:

```bash
Admin - admin:admin
User - user:user
Operator - operator:operator
```

There are six endpoint(s) developed for this demo:

```bash
/oauth/token - token generation api with unrestricted access(A valid username and password should be passed in the request body for attaining JWT token with specific Authority)

GET - api/v1/vehicle/ - returns all the vehicle information for an authenticated user (a valid JWT token must be present in the request header). Any user can with authority ('ROLE_USER', 'ROLE_ADMIN', 'ROLE_OPERATOR') can access this API

GET - api/v1/vehicle/{vehicleId} - returns specific vehicle information with id for an authenticated user (a valid JWT token must be present in the request header). Any user can with authority ('ROLE_USER', 'ROLE_ADMIN', 'ROLE_OPERATOR') can access this API

POST - /api/v1/vehicle - Saves the given vehicle information to the database for an authenticated user (a valid JWT token must be present in the request header). Only Users with with 'ROLE_USER' and 'ROLE_ADMIN' authorities can perform this action.

PUT - /api/v1/vehicle - Updates the given vehicle information to the database for an authenticated user (a valid JWT token must be present in the request header). Only Users with 'ROLE_USER' and 'ROLE_ADMIN' authorities can perform this action.

DELETE - /api/v1/vehicle/{vehicleID} - deletes the specific vehicle information with id from the database for an authenticated user (a valid JWT token must be present in the request header). Only Users with 'ROLE_ADMIN' authority can perform this action.

```

### Generating password hashes for new users

I'm using [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) to encode passwords. Your can generate your hashes with this simple 
tool: [Bcrypt Generator](https://www.bcrypt-generator.com)

### Using another database

Actually this demo is using an embedded H2 database that is automatically configured by Spring Boot. If you want to connect 
to another database you have to specify the connection in the *application.properties* in the resource directory. Here is the sample configuration for the purpose of demo:

```
### H2 Data source config ###
spring.datasource.url=jdbc:h2:mem:default
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.datasource.initialization-mode=always
spring.jpa.hibernate.ddl-auto=none

### Enabling the H2 console ### 
spring.h2.console.enabled=true

```

## Questions
If you have project related questions please create a new ticket with your question here [Create Issue](https://github.com/MaheshIare/spring-boot-jwt-security/issues)


## Author

**Mahesh Kumar Gutam**

* [Github](https://github.com/MaheshIare)

## Feedback
Please feel free to send me some feedback or questions!