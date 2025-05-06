# Task Tracker Backend

A Spring Boot application for managing tasks and task lists with secure JWT authentication, PostgreSQL database, and ready-to-use Docker and Kubernetes deployment.

## Features

- User registration and authentication (JWT)
- Role-based access (Admin/User)
- CRUD operations for Task Lists and Tasks
- API documentation with Swagger UI (OpenAPI)
- Containerized with Docker
- Kubernetes deployment manifests

## Technologies

- **Backend:** Spring Boot 3.4, Java 20
- **Database:** PostgreSQL (H2 for tests)
- **Security:** Spring Security, JWT
- **API Docs:** Springdoc OpenAPI/Swagger UI
- **Deployment:** Docker, Kubernetes

## Getting Started

### Prerequisites

- Java 20+
- Maven
- Docker (for containerization)
- Kubernetes (for orchestration, e.g., Minikube or Docker Desktop)

### Running Locally

1. **Clone the repository**

   ```sh
   git clone https://github.com/RaidThabet/task-tracker
   cd backend
   ```

2. **Configure the database**

   By default, the app expects a PostgreSQL database. You can use Docker Compose:

   ```sh
   docker compose -f docker/docker-compose.yaml up
   ```

3. **Build and run the application**

   ```sh
   ./mvnw clean package
   java -jar target/tasks-0.0.1-SNAPSHOT.jar
   ```

   The app will start on `http://localhost:8080`.

### API Documentation

- Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Running with Docker

1. **Build the Docker image**

   ```sh
   docker build -t tasks-tracker -f docker/Dockerfile .
   ```

2. **Start services**

   ```sh
   docker compose -f docker/docker-compose.yaml up
   ```

### Running on Kubernetes

1. **Apply manifests**

   ```sh
   kubectl apply -f kubernetes/
   ```

2. **Access the application**

   - The backend service is exposed via NodePort (default: 30005).
   - Swagger UI: `http://<node-ip>:30005/swagger-ui/index.html`

## Environment Variables

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SPRING_JPA_HIBERNATE_DDL_AUTO`
- `SPRINGDOC_SWAGGER_UI_ENABLED`
- `SPRINGDOC_API_DOCS_ENABLED`

See `src/main/resources/application.yaml` and deployment files for details.

## Testing

Run tests with:

```sh
./mvnw test
```
