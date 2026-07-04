# 🛒 CoreCart — Spring Boot E-Commerce Backend

A backend REST API for an e-commerce platform built with Java and Spring Boot.  
Covers secure authentication, role-based access control, and a complete cart-to-order workflow.

---

## ✨ Features

### Authentication & Security
- JWT-based stateless authentication
- Role-based access control — `ADMIN` (manage products) and `USER` (cart & orders)
- Password encryption with BCrypt
- Spring Security 6 with custom filter chain

### Product Management (Admin)
- Create, update, and delete products
- Fetch all products with basic filtering

### Shopping Cart & Orders (User)
- Add and remove items from cart
- Place orders from cart
- View order history

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3, Spring Security 6 |
| ORM | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Build Tool | Maven |
| API Testing | Postman |

---

## 🚀 Running Locally

### Prerequisites
- Java 21+
- PostgreSQL running locally
- Maven 3.8+

### Steps

```bash
git clone https://github.com/radhika4229/CoreCart.git
cd CoreCart
```

Create a PostgreSQL database:

```sql
CREATE DATABASE corecart_db;
```

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/corecart_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your-secret-key-minimum-32-characters
jwt.expiration=3600000
```

Run the application:

```bash
mvn spring-boot:run
```

Server starts at `http://localhost:8080`

---

## 📚 API Endpoints

Full Postman collection is available in the `/postman` folder.

### Auth

| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/auth/register` | Public | Register new user |
| POST | `/api/auth/login` | Public | Login, returns JWT |

### Products

| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/products` | Public | Get all products |
| POST | `/api/admin/products` | ADMIN | Create product |
| PUT | `/api/admin/products/{id}` | ADMIN | Update product |
| DELETE | `/api/admin/products/{id}` | ADMIN | Delete product |

### Cart

| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/cart` | USER | View cart |
| POST | `/api/cart/items` | USER | Add item to cart |
| DELETE | `/api/cart/items/{id}` | USER | Remove item |

### Orders

| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/orders` | USER | Place order from cart |
| GET | `/api/orders` | USER | View order history |

---

## 📁 Project Structure

```
CoreCart/
├── src/
│   └── main/
│       ├── java/com/corecart/
│       │   ├── controller/       # REST endpoints
│       │   ├── service/          # Business logic
│       │   ├── repository/       # Data access layer
│       │   ├── entity/           # JPA entities
│       │   ├── dto/              # Request/Response objects
│       │   ├── security/         # JWT filter, config
│       │   └── exception/        # Global exception handling
│       └── resources/
│           └── application.properties
├── postman/
│   └── CoreCart_API.postman_collection.json
├── pom.xml
└── README.md
```

---

## 🔍 Key Implementation Details

- **JWT filter** intercepts every request, validates token, sets SecurityContext
- **DTO pattern** — entities never exposed directly in API responses
- **Global exception handler** with `@ControllerAdvice` returns consistent error structure
- **5 JPA entities** — User, Product, Cart, CartItem, Order — with proper relationships and cascade rules
- **Normalised PostgreSQL schema** — foreign keys, no data duplication

---

## 📬 API Testing

Import the Postman collection from `/postman/CoreCart_API.postman_collection.json`  
to test all endpoints with pre-configured headers and sample request bodies.

---

## 👩‍💻 Author

**Radhika Sishodiya**  
[GitHub](https://github.com/radhika4229) · [LinkedIn](https://linkedin.com/in/radhika-sishodiya) · sishodiyaradhika@gmail.com
