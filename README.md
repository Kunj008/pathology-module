# Pathology Lab Management - Backend (Spring Boot)

This is the backend service for the Pathology Lab Management System.

## Prerequisites
- **Java 17** or higher
- **Maven 3.6+**
- **MySQL 8.0+**

## Getting Started

### 1. Database Setup
Create a database named `pathology_db` in MySQL:
```sql
CREATE DATABASE pathology_db;
```

Update your database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 2. Configure CORS
If you are running the frontend on a different port than `4200`, update the following in `application.properties`:
```properties
cors.allowed-origins=http://localhost:4200
```

### 3. Build & Run
Run the application using Maven:
```bash
./mvnw spring-boot:run
```
The server will start at `http://localhost:8080`.

### 4. API Documentation
Once the server is running, you can view the Swagger UI at:
`http://localhost:8080/swagger-ui.html`
