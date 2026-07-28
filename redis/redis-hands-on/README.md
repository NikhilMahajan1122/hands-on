# Spring Boot Redis Hands-On

A hands-on Spring Boot project demonstrating how to integrate Redis with Spring Boot for high-performance caching and in-memory data storage.

This project covers Redis fundamentals, caching strategies, and Spring Cache annotations through practical examples.

---

## 🚀 Tech Stack

- Java
- Spring Boot
- Spring Data Redis
- Spring Data JPA
- MySQL
- Redis
- Maven

---

## 📚 Topics Covered

### Redis Data Types
- ✅ String
- ✅ Hash
- ⏳ List (Introduction)
- ⏳ Set (Introduction)

### Redis Features
- ✅ TTL (Time To Live)
- ✅ Cache Aside Pattern
- ✅ RedisTemplate
- ✅ StringRedisTemplate

### Spring Boot Caching
- ✅ @Cacheable
- ✅ @CachePut
- ✅ @CacheEvict

---

## 📂 Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── config
└── resources
```

---

## 🛠 Prerequisites

- Java 17
- Maven
- MySQL
- Redis

---

## ▶️ Getting Started

### Clone the repository

```bash
git clone https://github.com/nikhilmahajan1122/hands-on/redis-hands-on.git
```

### Start Redis

```bash
docker run --name redis -p 6379:6379 -d redis
```

### Create MySQL Database

```sql
CREATE DATABASE redis_demo;
```

### Configure application.yaml

Update the datasource and Redis connection details as per your environment.

### Run the application

```bash
mvn spring-boot:run
```

---

## 📖 Learning Roadmap

- [ ] Configure Redis with Spring Boot
- [ ] Practice String operations
- [ ] Practice Hash operations
- [ ] Work with TTL
- [ ] Implement Cache Aside Pattern
- [ ] Explore RedisTemplate
- [ ] Explore StringRedisTemplate
- [ ] Implement @Cacheable
- [ ] Implement @CachePut
- [ ] Implement @CacheEvict
- [ ] Practice List operations
- [ ] Practice Set operations

---

## 🎯 Goal

The objective of this repository is to gain practical experience with Redis and understand how it is used in Spring Boot applications for caching and improving application performance.

---

## 📌 Future Enhancements

- Redis Pub/Sub
- Distributed Locking
- Rate Limiting
- Session Management
- Redis Streams
- Docker Compose Integration
- Unit & Integration Tests

---

## 📄 License

This project is created for learning and demonstration purposes.