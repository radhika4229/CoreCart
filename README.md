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

👤 Author

Radhika Sishodiya
Backend Developer (Java | Spring Boot)
