# 💖 Lovable App Backend (Spring Boot)

A backend system for a **dating/matching application (Tinder-like)** built using **Spring Boot**, featuring authentication, matching logic, and real-time-ready messaging APIs.

---

## 🚀 Features

* 🔐 **Authentication & Security**

  * User Registration & Login
  * JWT-based authentication
  * Spring Security integration

* 👤 **User System**

  * User profile management
  * UUID-based user IDs

* ❤️ **Like System**

  * Like other users
  * Prevent duplicate likes

* 💘 **Match System**

  * Mutual likes → automatic match
  * Duplicate match prevention
  * Consistent user pairing logic

* 💬 **Messaging System**

  * Send messages between matched users
  * Fetch chat history
  * Messages ordered by timestamp

---

## 🧱 Tech Stack

* **Backend:** Spring Boot
* **Security:** Spring Security + JWT
* **Database:** PostgreSQL
* **ORM:** Spring Data JPA (Hibernate)
* **Build Tool:** Maven

---

## 📁 Project Structure

```
com.example.lovable
│
├── config        # Security & JWT filter
├── controller    # REST APIs
├── service       # Business logic
├── repository    # Database access
├── entity        # JPA entities
├── dto           # Request/Response objects
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the repository

```bash
git clone https://github.com/parsh1002/lovable-app-backend.git
cd lovable-app-backend
```

---

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lovable
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

---

### 3️⃣ Run the Application

```bash
./mvnw spring-boot:run
```

App runs on:

```
http://localhost:8081
```

---

## 🔐 Authentication Flow

1. Register user
2. Login → receive JWT token
3. Use token in headers:

```
Authorization: Bearer <your_token>
```

---

## 📡 API Endpoints

### 🔑 Auth

| Method | Endpoint         | Description     |
| ------ | ---------------- | --------------- |
| POST   | `/auth/register` | Register user   |
| POST   | `/auth/login`    | Login & get JWT |

---

### ❤️ Likes

| Method | Endpoint                   | Description |
| ------ | -------------------------- | ----------- |
| POST   | `/api/likes/like/{userId}` | Like a user |

---

### 💘 Matches

| Logic | Description                             |
| ----- | --------------------------------------- |
| Auto  | Created when both users like each other |

---

### 💬 Messages

| Method | Endpoint                     | Description      |
| ------ | ---------------------------- | ---------------- |
| POST   | `/api/messages/{receiverId}` | Send message     |
| GET    | `/api/messages/{userId}`     | Get chat history |

---

## 🧠 Core Logic Highlights

* Mutual likes trigger match creation
* UUID-based ordering ensures unique matches
* JWT filter secures all protected routes
* Stateless authentication (no sessions)

---

## 🧪 Example Request

### Send Message

```
POST /api/messages/{receiverId}
```

Headers:

```
Authorization: Bearer <token>
```

Body:

```json
{
  "content": "Hello! How are you?"
}
```

---

## ⚠️ Important Notes

* All protected endpoints require JWT
* `/auth/**` endpoints are public
* Uses stateless authentication (no sessions)

---

## 🚀 Future Improvements

* WebSocket for real-time chat
* Redis caching
* Microservices architecture
* Pagination & search
* Notifications system

---

## 💡 What This Project Demonstrates

* Secure backend API design
* Real-world system design (matching + chat)
* Clean architecture using Spring Boot
* Scalable authentication using JWT

---

## 👨‍💻 Author

**Parshva Jain**

---

## ⭐ If you like this project

Give it a ⭐ on GitHub and feel free to contribute!
