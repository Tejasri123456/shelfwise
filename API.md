# API overview

Base URL: `/api`

## Public
- `POST /auth/register` — `{ "username":"reader1", "email":"reader@example.com", "password":"password123" }`
- `POST /auth/login` — `{ "username":"reader1", "password":"password123" }`; response includes JWT token and role.
- `GET /books?page=0&size=10&q=java` — paginated catalogue (public).
- `POST /chat` — `{ "message":"Do you have Java books?" }`; catalogue retrieval, optional Gemini generation.

## Authenticated (`Authorization: Bearer <token>`)
- `GET /borrow/mine`
- `POST /borrow/{bookId}`
- `POST /borrow/return/{recordId}`

## Admin only
- `POST /books`, `PUT /books/{id}`, `DELETE /books/{id}`
- `GET /borrow`

Interactive API docs: `/swagger-ui/index.html`.
