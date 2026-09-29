# Online Banking System

A web-based banking application built with **Java, Spring Boot, Spring Security, PostgreSQL, and Thymeleaf**, implementing secure user authentication, account operations, fund transfers, and transaction management.

## Features

* User registration and login
* Secure password hashing with BCrypt
* Spring Security authentication and authorization
* Role-based access control
* User dashboard
* Fund transfers between accounts
* Transaction history
* Database persistence with PostgreSQL
* H2 support for development
* Transaction management using `@Transactional`
* Server-side rendered UI with Thymeleaf

## Tech Stack

| Technology      | Purpose                        |
| --------------- | ------------------------------ |
| Java            | Backend development            |
| Spring Boot     | Application framework          |
| Spring Security | Authentication & authorization |
| Spring Data JPA | Database persistence           |
| PostgreSQL      | Primary database               |
| H2              | Development database           |
| Thymeleaf       | Web UI                         |
| Maven           | Build & dependency management  |
| Git & GitHub    | Version control                |

## Architecture

The application follows a layered architecture:

```text
                    ┌─────────────────┐
                    │   Thymeleaf UI  │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   Controllers   │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │    Services     │
                    │ Business Logic  │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   Repositories  │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   PostgreSQL    │
                    └─────────────────┘

              ┌──────────────────────────┐
              │      Spring Security     │
              │ Authentication & RBAC    │
              └──────────────────────────┘
```

### Project Structure

```text
src/main/java/com/bank/onlineBanking/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── DashboardController.java
│   ├── HomeController.java
│   └── TransactionController.java
├── model/
│   ├── User.java
│   └── Transaction.java
├── repository/
│   ├── UserRepository.java
│   └── TransactionRepository.java
├── security/
│   └── CustomUserDetailsService.java
└── service/
    ├── UserService.java
    └── TransactionService.java
```

## Security

The application uses **Spring Security** for authentication and authorization.

* BCrypt password encryption
* Authenticated access to protected pages
* Role-based authorization
* Custom `UserDetailsService`
* Login and logout handling

> Development-only configurations such as the H2 console should not be exposed in a production environment.

## Database

The application supports **PostgreSQL** as the primary database and **H2** for development.

Configure the database in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/online_banking_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Do not commit real credentials or secrets to the repository.

## Getting Started

### Prerequisites

* Java 17+
* Maven 3.9+
* PostgreSQL

### Clone the Repository

```bash
git clone https://github.com/prince-07-create/OnlineBankingProject.git
cd OnlineBankingProject
```

### Create Database

```sql
CREATE DATABASE online_banking_db;
```

Update your PostgreSQL credentials in `application.properties`.

### Run the Application

```bash
mvn spring-boot:run
```

Or on Windows:

```bash
mvnw.cmd spring-boot:run
```

Open:

```text
http://localhost:8080
```

## Testing

Run the test suite with:

```bash
mvn test
```

## Future Improvements

* DTO-based request/response architecture
* Global exception handling
* Expanded unit and integration testing
* OpenAPI/Swagger documentation
* Improved security hardening
* Centralized logging
* Docker and CI/CD integration

## Disclaimer

This is a **portfolio and educational project**. It is not intended for processing real financial transactions or storing real banking/customer data.

## Author

**Prince Kumar Thakur**

Java & Spring Boot Backend Developer | REST APIs | AI & Automation

[GitHub](https://github.com/prince-07-create)
