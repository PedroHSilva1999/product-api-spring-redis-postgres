# Products API

A RESTful API for managing products built with Spring Boot, featuring PostgreSQL database and Redis caching capabilities.

## Table of Contents

- [Overview](#overview)
- [Technologies](#technologies)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Using Docker (Recommended)](#using-docker-recommended)
  - [Local Development](#local-development)
- [API Endpoints](#api-endpoints)
- [Data Model](#data-model)
- [Configuration](#configuration)
- [Project Structure](#project-structure)
- [Testing](#testing)

## Overview

Products API is a Spring Boot application that provides a complete CRUD (Create, Read, Update, Delete) interface for managing products. The application uses PostgreSQL as the primary database and Redis for caching to improve performance.

## Technologies

- **Java 17**
- **Spring Boot 4.0.1**
- **Spring Data JPA** - Database persistence
- **PostgreSQL** - Relational database
- **Redis** - Caching layer
- **Lombok** - Reducing boilerplate code
- **Docker** - Containerization
- **Maven** - Dependency management

## Features

- Full CRUD operations for products
- Product search by ID and/or name
- Redis-based caching for improved performance
- RESTful API design
- Docker Compose setup for easy deployment
- Health checks for database and cache services
- Automatic database schema updates

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6+**
- **Docker** and **Docker Compose** (for containerized deployment)

## Getting Started

### Using Docker (Recommended)

The easiest way to run the application is using Docker Compose, which will set up all required services (PostgreSQL, Redis, pgAdmin, and the application) automatically.

1. **Build the application JAR file:**
   ```bash
   mvn clean package -DskipTests
   ```

2. **Start all services with Docker Compose:**
   ```bash
   docker-compose up --build
   ```

   This command will:
   - Build the Docker image for the application
   - Start PostgreSQL database
   - Start Redis cache
   - Start pgAdmin (database management UI)
   - Start the Spring Boot application

3. **Access the application:**
   - API Base URL: `http://localhost:8080`
   - pgAdmin: `http://localhost:1532` (email: `admin@admin.com`, password: `admin`)
   - PostgreSQL: `localhost:5432`
   - Redis: `localhost:6379`

4. **Stop all services:**
   ```bash
   docker-compose down
   ```

### Local Development

If you prefer to run the application locally without Docker:

1. **Start PostgreSQL and Redis:**
   - Ensure PostgreSQL is running on `localhost:5432`
   - Ensure Redis is running on `localhost:6379`
   - Create a database named `productsDB`
   - Use credentials: username `admin`, password `admin`

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

   Or build and run the JAR:
   ```bash
   mvn clean package
   java -jar target/ProductsAPI-0.0.1-SNAPSHOT.jar
   ```

## API Endpoints

All endpoints are prefixed with `/products`.

### Create Product
- **POST** `/products`
- **Request Body:**
  ```json
  {
    "name": "Product Name",
    "quantity": 10,
    "price": 29.99
  }
  ```
- **Response:** Created product with generated ID and timestamp

### List All Products
- **GET** `/products`
- **Response:** List of all products (cached in Redis)
- **Response Body:**
  ```json
  [
    {
      "id": "uuid",
      "name": "Product Name",
      "quantity": 10,
      "price": 29.99,
      "createdAt": "2024-01-01T10:00:00"
    }
  ]
  ```

### Search Products
- **GET** `/products/search`
- **Query Parameters:**
  - `id` (optional): Product ID
  - `name` (optional): Product name
- **Examples:**
  - Search by ID: `/products/search?id=uuid-here`
  - Search by name: `/products/search?name=Product%20Name`
  - Search by both: `/products/search?id=uuid-here&name=Product%20Name`
- **Response:** List of matching products (cached in Redis)

### Update Product
- **PUT** `/products/{id}`
- **Path Variable:** `id` - Product ID
- **Request Body:**
  ```json
  {
    "name": "Updated Product Name",
    "quantity": 20,
    "price": 39.99
  }
  ```
- **Response:** Updated product

### Delete Product
- **DELETE** `/products/{id}`
- **Path Variable:** `id` - Product ID
- **Response:** 200 OK (no content)

## Data Model

### ProductModel

| Field      | Type           | Description                    |
|------------|----------------|--------------------------------|
| id         | String (UUID)  | Unique identifier (auto-generated) |
| name       | String         | Product name                   |
| quantity   | Integer        | Available quantity             |
| price      | Double         | Product price                  |
| createdAt  | LocalDateTime  | Creation timestamp (auto-generated) |

## Configuration

### Application Configuration

The application configuration is located in `src/main/resources/application.yaml`:

```yaml
spring:
  application:
    name: Products API
  
  datasource:
    url: jdbc:postgresql://localhost:5432/productsDB
    username: admin
    password: admin
  
  jpa:
    database: POSTGRESQL
    hibernate:
      ddl-auto: update
    show-sql: true
  
  data:
    redis:
      host: ${SPRING_REDIS_HOST:localhost}
      port: ${SPRING_REDIS_PORT:6379}
      timeout: 2000ms
```

### Docker Compose Configuration

The `compose.yaml` file defines the following services:

- **postgres**: PostgreSQL database
  - Database: `productsDB`
  - User: `admin`
  - Password: `admin`
  - Port: `5432`

- **pgadmin**: Database administration tool
  - Port: `1532`
  - Email: `admin@admin.com`
  - Password: `admin`

- **redis**: Redis cache server
  - Port: `6379`

- **app**: Spring Boot application
  - Port: `8080`
  - Environment variables are automatically configured for Docker networking

## Project Structure

```
ProductsAPI/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/products/ProductsAPI/
│   │   │       ├── ProductsApiApplication.java
│   │   │       ├── controller/
│   │   │       │   └── ProductController.java
│   │   │       ├── service/
│   │   │       │   └── ProductService.java
│   │   │       ├── repository/
│   │   │       │   └── ProductRepository.java
│   │   │       └── domain/
│   │   │           └── entity/
│   │   │               └── ProductModel.java
│   │   └── resources/
│   │       └── application.yaml
│   └── test/
│       └── java/
│           └── com/products/ProductsAPI/
│               └── ProductsApiApplicationTests.java
├── compose.yaml
├── Dockerfile
├── pom.xml
└── README.md
```

## Testing

To run the test suite:

```bash
mvn test
```

To skip tests during build:

```bash
mvn clean package -DskipTests
```

## License

This project is provided as-is for educational and development purposes.
