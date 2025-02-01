JWT Authentication with Spring Boot
This project demonstrates a simple JWT authentication system using Spring Boot. It provides endpoints for user registration and login, utilizing JSON Web Tokens (JWT) for stateless authentication.

Features
User Registration: Users can register with a username and password.
User Login: Users can log in and receive a JWT token.
JWT Authentication: JWT is used for securing the API, with token validation for subsequent requests.
Role-Based Access: Users are assigned roles and can have different levels of access.
Technologies Used
Spring Boot: The main framework used for building the RESTful API.
Spring Security: Provides authentication and authorization.
JWT (JSON Web Token): For secure, stateless authentication.
H2 Database: In-memory database for storing users and roles (can be replaced with any database like MySQL, PostgreSQL).
Lombok: For reducing boilerplate code in DTOs and models.
Setup
Prerequisites
Java 17 or higher
Maven or Gradle
An IDE like IntelliJ IDEA or Eclipse
Installation
Clone the repository:

bash
Копировать
Редактировать
git clone https://github.com/yourusername/jwtauthentication.git
cd jwtauthentication
Install dependencies:

If using Maven:

bash
Копировать
Редактировать
mvn clean install
If using Gradle:

bash
Копировать
Редактировать
gradle build
Run the application:

bash
Копировать
Редактировать
mvn spring-boot:run
Or if using Gradle:

bash
Копировать
Редактировать
gradle bootRun
The application will run on http://localhost:8080.

API Endpoints
Register a User
Endpoint: /api/v1/auth/register

Method: POST

Request Body:

json
Копировать
Редактировать
{
  "username": "user1",
  "password": "password123"
}
Response:

json
Копировать
Редактировать
{
  "message": "User successfully registered!"
}
Login and Get JWT Token
Endpoint: /api/v1/auth/login

Method: POST

Request Body:

json
Копировать
Редактировать
{
  "username": "user1",
  "password": "password123"
}
Response:

json
Копировать
Редактировать
{
  "accessToken": "your-jwt-token-here",
  "tokenType": "Bearer "
}
Protected Endpoint (Requires Authentication)
Endpoint: /api/v1/protected

Method: GET

Headers: Authorization: Bearer <your-jwt-token>

Response:

json
Копировать
Редактировать
{
  "message": "Access granted!"
}
Security
JWT tokens are used to authenticate users and protect the API. Upon login, a JWT token is generated and returned to the user. This token must be sent in the Authorization header for subsequent requests to protected endpoints.

Role Management
Roles: Users are assigned the role USER by default during registration.
You can customize roles by modifying the RoleRepository and adding more roles as needed.
Configuration
The JWT expiration time and secret key can be configured in the Constants class:

java
Копировать
Редактировать
public static final long JWT_EXPIRATION = 86400; // 1 day
public static final String JWT_SECRET = "jwt_secret";
License
This project is licensed under the MIT License - see the LICENSE file for details.
