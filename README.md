# Library Management System (Full-stack capstone)

A learning-oriented full-stack starter aligned to Units 1–10 in the provided lesson plan.

## Stack
- React + Vite, React Router, Axios
- Java 17, Spring Boot 3, Spring Web, Spring Data JPA, Validation, Spring Security, JWT, BCrypt
- MySQL, Swagger/OpenAPI, Actuator, JUnit/Mockito
- Docker Compose
- Library assistant with database retrieval (RAG-style context retrieval); optional Gemini API generation when `GEMINI_API_KEY` is configured

## Run locally
1. Install Java 17+, Node.js 20+, and MySQL 8+ (or use Docker).
2. Create `library_db` in MySQL, or start the whole stack with Docker Compose.
3. Backend: `cd backend` then `mvn spring-boot:run`.
4. Frontend: `cd frontend` then `npm install` and `npm run dev`.
5. Open the Vite URL (normally http://localhost:5173).

Default Docker MySQL credentials are for local development only. Change them before any public deployment.

## Docker
From project root: `docker compose up --build`. Frontend at http://localhost:5173, backend at http://localhost:8080, Swagger at http://localhost:8080/swagger-ui/index.html.

## Seed an admin
Register a user through the UI. For a local demo, use the registration endpoint and then change the user's role to `ADMIN` in MySQL:
`UPDATE users SET role='ADMIN' WHERE username='your_username';`
Never expose database credentials or JWT secrets in source control.

## Units covered
1. Vite/React components, Spring Boot layers, REST APIs.
2. Book CRUD, dynamic table, loading/error states, pagination.
3. Registration form validation, DTOs, API exception handling.
4. JPA user/book/borrow relations, borrow and return workflows.
5. Login, role enum, protected frontend routes and role-based APIs.
6. JWT filter, Spring Security, BCrypt, `@PreAuthorize`.
7. Chat UI, conversation history, database retrieval, optional Gemini API.
8. Caching, async audit log, Actuator health/metrics, retry-friendly API client.
9. JUnit/Mockito test, production frontend build, Dockerfile and Compose.
10. Swagger/OpenAPI, deployment notes, integrated capstone.

This is a starter capstone, not a production-audited system. Review security, privacy, backups, and deployment settings before public use.
