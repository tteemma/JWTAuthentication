# ![JWT Authentication](https://user-images.githubusercontent.com/74038190/212284094-e50ceae2-de86-4dd6-9f9c-a3ebcb3ede9e.gif)


# JWT Authentication with Spring Boot

This project demonstrates how to implement JWT authentication in a Spring Boot application. It provides endpoints for user registration and login, and uses JSON Web Tokens (JWT) for stateless authentication.

## Features

- **User Registration**: Register a user with a username and password.
- **User Login**: Log in and receive a JWT token for authentication.
- **JWT Authentication**: Use JWT tokens to secure API requests and manage user sessions.
- **Role-Based Access Control**: Assign roles to users for controlled access to different endpoints.

## Technologies Used

- **Spring Boot**: A Java-based framework for building the RESTful API.
- **Spring Security**: Handles authentication and authorization within the app.
- **JWT (JSON Web Token)**: Used for secure, stateless user authentication.
- **H2 Database** (optional): An in-memory database for storing users and roles (can be replaced with MySQL, PostgreSQL, etc.).
- **Lombok**: A Java library to reduce boilerplate code (e.g., getter/setter methods).

## Setup

### Prerequisites

Before setting up the project, make sure you have the following installed:

- Java 17 or higher
- Maven or Gradle
- An IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. **Clone the repository**:

    ```bash
    git clone https://github.com/tteemma/jwtauthentication.git
    cd jwtauthentication
    ```

2. **Install dependencies**:

    If you're using Maven:

    ```bash
    mvn clean install
    ```

    Or if you're using Gradle:

    ```bash
    gradle build
    ```

3. **Run the application**:

    To run the application with Maven:

    ```bash
    mvn spring-boot:run
    ```

    Or with Gradle:

    ```bash
    gradle bootRun
    ```

    By default, the application will be running on `http://localhost:8080`.

## API Endpoints

### 1. Register a User

- **Endpoint**: `/api/v1/auth/register`
- **Method**: `POST`
- **Request Body**:

    ```json
    {
      "username": "user1",
      "password": "password123"
    }
    ```

- **Response**:

    ```json
    {
      "message": "User successfully registered!"
    }
    ```

### 2. Login and Get JWT Token

- **Endpoint**: `/api/v1/auth/login`
- **Method**: `POST`
- **Request Body**:

    ```json
    {
      "username": "user1",
      "password": "password123"
    }
    ```

- **Response**:

    ```json
    {
      "accessToken": "your-jwt-token-here",
      "tokenType": "Bearer "
    }
    ```

### 3. Access Protected Resource (Requires Authentication)

- **Endpoint**: `/api/v1/protected`
- **Method**: `GET`
- **Headers**: `Authorization: Bearer <your-jwt-token>`

- **Response**:

    ```json
    {
      "message": "Access granted!"
    }
    ```

## Security

JWT (JSON Web Token) is used for user authentication and authorization. After a successful login, a JWT token is returned. This token must be included in the `Authorization` header as `Bearer <token>` for all subsequent requests to protected endpoints.

### Example Request with JWT

To access protected resources, include the JWT token in the `Authorization` header as follows:

```bash
curl -X GET "http://localhost:8080/api/v1/protected" -H "Authorization: Bearer <your-jwt-token>"
