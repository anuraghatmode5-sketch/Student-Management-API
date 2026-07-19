# 🎓 Student Management REST API

A REST API built with **Spring Boot** for managing student records with full CRUD operations.

## 🛠️ Tech Stack
Java | Spring Boot | Spring Data JPA | MySQL | Maven

## 📌 Features
- ✅ CRUD operations (Create, Read, Update, Delete)
- ✅ Soft Delete support
- ✅ DTO pattern for clean API responses
- ✅ Global Exception Handling
- ✅ Custom exceptions with proper HTTP status codes

## 📡 API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/students | Create student |
| GET | /api/students | Get all students |
| GET | /api/students/{id} | Get by ID |
| PUT | /api/students/{id} | Update student |
| DELETE | /api/students/{id} | Delete student |

## 🚀 How to Run
1. Create MySQL database `student_db`
2. Update `application.properties` with your credentials
3. Run and test via Postman on `http://localhost:8080`
