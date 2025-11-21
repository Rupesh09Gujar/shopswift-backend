ShopSwift - E-Commerce Backend (Spring Boot)

This is a e-commerce backend project that I built to practice Java Spring Boot, REST APIs, and MySQL.
It includes basic features like user authentication, product APIs, cart system, and order checkout.

I created this project to improve my backend development skills and to understand how a real-world backend works.

----- Features -----

User signup and login

JWT authentication

Product listing

Add to cart / remove from cart

Checkout and place order

MySQL database

Swagger UI for API documentation

----- Tech Stack -----

Java 17

Spring Boot 3

Spring Security

JPA / Hibernate

MySQL

Swagger (OpenAPI)

Maven

----- API Documentation ------

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html


This contains all the available endpoints with request/response details.

▶️ How to Run the Project
1. Clone the repository
git clone https://github.com/YOUR_USERNAME/shopswift-backend.git

2. Create the database in MySQL
CREATE DATABASE shopswift_db;

3. Update application.properties

Add your MySQL username and password.

4. Run the application
mvn spring-boot:run

Testing the APIs

I have also added a Postman Collection which contains all the APIs:

Signup

Login

Products

Cart

Orders

You can import it directly in Postman to test the project.

Database Tables Used

users

products

carts

cart_items

orders

order_items

These tables handle the full flow from user → cart → order.

 Why I built this

I made this project to:

Practice Spring Boot

Understand authentication

Learn about REST API design

Work with MySQL

Implement real-world features like cart and checkout

This project really helped me understand how backend systems work.
