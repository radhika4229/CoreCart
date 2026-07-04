🛒 CoreCart – Spring Boot E-Commerce Backend

CoreCart is a Spring Boot backend e-commerce application implementing JWT authentication, role-based authorization, and a complete cart-to-order workflow.

The project focuses on backend architecture, security, and data integrity, not UI.

🚀 Features

JWT-based User Authentication

Role-Based Access Control (ADMIN, USER)

Product & Image Management (Admin)

Cart Management & Order Placement (User)

Secure APIs with Spring Security

PostgreSQL Integration

🛠 Tech Stack

Java · Spring Boot · Spring Security · JWT · JPA (Hibernate) · PostgreSQL · Maven · Postman

🔐 Roles

ADMIN: Manage products and images
USER: Manage cart and place orders

Unauthorized access is restricted using JWT and Spring Security.

🔄 Flow

Login → JWT Token → Product Management (Admin) → Cart (User) → Order Placement

🗂 Database

Entities: User, Role, Product, Image, Cart, CartItem, Order, OrderItem
📌 ER Diagram: /docs/ERD.png

📸 API Documentation

All APIs are verified using Postman screenshots.

⚙️ Run Locally
git clone <repository-url>
cd CoreCart
mvn spring-boot:run


Server: http://localhost:8080

📁 Structure
CoreCart/
 ├─ src/
 ├─ pom.xml
 ├─ postman/
 └─ docs/

2. Database Setup
Copy-- Create database
CREATE DATABASE corecart_db;

-- Create user (optional, recommended for security)
CREATE USER corecart_user WITH PASSWORD 'your_secure_password';
GRANT ALL PRIVILEGES ON DATABASE corecart_db TO corecart_user;
3. Configure Environment Variables
Copycp .env.example .env
Edit .env:

Copy# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/corecart_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=your-super-secret-key-minimum-256-bits-use-strong-random-string
jwt.expiration=3600000  # 1 hour

# Server
server.port=8080
4. Run Application
Copymvn spring-boot:run
Server starts at http://localhost:8080

📚 API Documentation
Authentication
Register User

POST /api/auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "SecurePass123!",
  "role": "USER"
}

Response: 201 Created
{
  "id": 1,
  "email": "john@example.com",
  "role": "USER",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
Login

POST /api/auth/login
{
  "email": "john@example.com",
  "password": "SecurePass123!"
}

Response: 200 OK
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "..."
}
Products (Admin Only)
Create Product

POST /api/admin/products
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 999.99,
  "stock": 50
}

Response: 201 Created
Get All Products

GET /api/products
Response: 200 OK
[
  {
    "id": 1,
    "name": "Laptop",
    "price": 999.99,
    "stock": 50
  }
]
Cart & Orders (User)
Add to Cart

POST /api/cart/items
Authorization: Bearer {token}
{
  "productId": 1,
  "quantity": 2
}
Place Order

POST /api/orders
Authorization: Bearer {token}
{
  "shippingAddress": "123 Main St, City, Country",
  "paymentMethod": "CARD"
}

Response: 201 Created
{
  "orderId": "ORD-2026-001",
  "totalAmount": 1999.98,
  "status": "PENDING"
}
Get Order History

GET /api/orders
Authorization: Bearer {token}
Response: 200 OK
[
  {
    "orderId": "ORD-2026-001",
    "orderDate": "2026-02-06T20:00:00Z",
    "totalAmount": 1999.98,
    "status": "COMPLETED"
  }
]
For complete Postman collection, see /postman/CoreCart_API.postman_collection.json

📊 Database Schema
Users Table
CopyCREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL DEFAULT 'USER',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
Products Table
CopyCREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  price DECIMAL(10, 2) NOT NULL,
  stock INT NOT NULL,
  image_url VARCHAR(500),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
Orders Table
CopyCREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  total_amount DECIMAL(10, 2),
  shipping_address VARCHAR(500),
  status VARCHAR(50) DEFAULT 'PENDING',
  FOREIGN KEY (user_id) REFERENCES users(id)
);
(Full ER Diagram in /docs/ERD.png)

🧪 Testing
Run unit tests:

Copymvn test
Run integration tests:

Copymvn verify
