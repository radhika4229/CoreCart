# CoreCart - Shopping Cart Backend (Spring Boot)

CoreCart is a Shopping Cart REST API built with Spring Boot and JPA/Hibernate.
It includes product & category management, cart operations, and order workflow.

> Security (Spring Security + JWT) is currently in progress.

## Tech Stack
- Java + Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Lombok
- ModelMapper

## Features
- Product CRUD
- Category CRUD
- Cart & CartItem
    - Add item
    - Remove item
    - Update quantity
    - Auto total calculation
- Order workflow
- Custom exceptions

## Run Locally

### Prerequisites
- Java 17+
- Maven
- PostgreSQL

### Setup
1. Create database: `Core_Cart`
2. Copy config:
    - `src/main/resources/application-example.properties`
    - rename to `application.properties`
    - fill your DB password and JWT secret
3. Start server:
```bash
mvn spring-boot:run
