# Auth Microservice

A lightweight and scalable authentication microservice built with Java and Spring Boot, designed to handle user authentication and authorization in a microservices architecture.

## Features

* **JWT Authentication**: Secure user authentication using JSON Web Tokens.
* **User Registration**: Allows users to register with email and password.
* **User Login**: Provides login functionality with token-based authentication.
* **Role-Based Access Control (RBAC)**: Supports user roles for fine-grained access control.
* **Docker Support**: Easily deployable using Docker for containerization.

## Technologies Used

* **Java 17**: Programming language for building the microservice.
* **Spring Boot**: Framework for creating stand-alone, production-grade Spring-based applications.
* **Spring Security**: Provides authentication and authorization capabilities.
* **JWT (JSON Web Tokens)**: Secure token-based authentication.
* **Docker**: Platform for developing, shipping, and running applications.

## Getting Started

### Prerequisites

* Java 17 or higher
* Maven
* Docker (optional, for containerization)

### Installation

Clone the repository:

```bash
git clone https://github.com/AlexNicolasCode/auth-microservice.git
cd auth-microservice
```

Build the project:

```bash
mvn clean install
```

### Running the Application

To run the application locally:

```bash
mvn spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

### Docker Deployment

To build and run the application using Docker:

```bash
docker-compose up --build
```

This will start the application in a Docker container.

## API Endpoints

### Authentication

* `POST /api/auth/register`: Register a new user.
* `POST /api/auth/login`: Login and receive a JWT token.

### User Management

* `GET /api/users`: Retrieve all users (Admin only).
* `GET /api/users/{id}`: Retrieve a user by ID (Admin only).

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository.

2. Create a new branch (`git checkout -b feature-branch`).

3. Make your changes.

4. Commit your changes using **Conventional Commits**:

   * **feat**: for new features
   * **fix**: for bug fixes
   * **docs**: for documentation changes
   * **style**: for code formatting or style changes
   * **refactor**: for code refactoring
   * **test**: for adding or updating tests
   * **chore**: for maintenance tasks

   Example: `git commit -m "feat: add user registration endpoint"`

5. Push to the branch (`git push origin feature-branch`).

6. Create a new Pull Request.
