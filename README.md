# Task Management API

A RESTful API for managing tasks built with Spring Boot.

## Technologies Used

- Java 21
- Spring Boot 3.5.10
- Spring Data JPA
- Spring Security
- PostgreSQL
- Maven

## Features

- User registration with password encryption
- Create, read, update, and delete tasks
- Toggle task status (PENDING/COMPLETED)
- Filter tasks by status
- Input validation
- Global error handling
- Swagger API documentation

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |

### Tasks

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/tasks?userId={id}` | Create task |
| GET | `/api/tasks?userId={id}` | Get all tasks |
| GET | `/api/tasks?userId={id}&status={status}` | Filter by status |
| GET | `/api/tasks/{taskId}` | Get single task |
| PUT | `/api/tasks/{taskId}` | Update task |
| DELETE | `/api/tasks/{taskId}` | Delete task |
| PATCH | `/api/tasks/{taskId}/status` | Toggle status |

## Getting Started

### Prerequisites

- Java 21 or higher
- PostgreSQL 14 or higher
- Maven

### Setup

1. Clone the repository:
```bash
git clone https://github.com/NickPro97/task-management-api.git
```

2. Create PostgreSQL database:
```sql
CREATE DATABASE taskmanager_db;
```

3. Update `application.yml` with your database credentials

4. Run the application:
```bash
mvn spring-boot:run
```

5. Access Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

## Author

**Nikhil Kumar**

- GitHub: [@NickPro97](https://github.com/NickPro97)