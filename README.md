CoreCart is a backend e-commerce application built using Spring Boot.
It implements JWT-based authentication, role-based authorization, and complete cart-to-order workflow following real-world backend design practices.

This project focuses on backend architecture, security, and data integrity rather than UI.

🚀 Features

User Registration & Login (JWT Authentication)

Role-Based Authorization (ADMIN, USER)

Product Management (Admin only)

Product Image Association

Cart Management (User only)

Order Placement

Secure API access using Spring Security

PostgreSQL database integration

🛠 Tech Stack

Language: Java

Framework: Spring Boot

Security: Spring Security + JWT

ORM: Spring Data JPA (Hibernate)

Database: PostgreSQL

Build Tool: Maven

API Testing: Postman

🔐 Roles & Access Control
ADMIN

Add / update / delete products

Upload product images

USER

Add products to cart

View cart

Place orders

Unauthorized access is restricted using Spring Security and JWT tokens.

🔄 Application Flow

User registers or logs in

JWT token is generated on successful login

ADMIN manages products and images

USER adds products to cart

USER places order

Order data is stored with order items for history tracking

🗂 Database Design

The database is designed using proper normalization and relationships.

Main entities:

User

Role

Product

Image

Cart

CartItem

Order

OrderItem

Each entity is connected using primary and foreign keys to maintain referential integrity.

📌 ER Diagram:
See /docs/ERD.png

📸 API Documentation (Screenshots)

API functionality is documented using Postman screenshots to verify that all endpoints work correctly.

⚙️ Setup Instructions
1️⃣ Clone Repository
git clone <repository-url>
cd CoreCart

2️⃣ Configure Database

Create a PostgreSQL database:

corecart


Update application.properties:

spring.datasource.url
spring.datasource.username
spring.datasource.password

3️⃣ Run Application
mvn spring-boot:run


Server runs at:

http://localhost:8080

🧪 API Testing Checklist

Authentication validation

Role-based access control

Product CRUD (Admin)

Cart operations (User)

Order placement

Negative test cases (403, 404, 401)

📁 Project Structure
CoreCart/
 ├─ src/
 ├─ pom.xml
 ├─ README.md
 ├─ postman/
 ├─ docs/
 │   └─ ERD.png

📌 Notes

JWT token is required for all protected endpoints

Admin and User roles are strictly enforced

Order items store snapshot data to preserve order history

The project is extensible for future modules like payments and shipping



Radhika Sishodiya
Computer Science Engineering Student
Backend Development (Java | Spring Boot)
