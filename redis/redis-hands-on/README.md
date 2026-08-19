# Spring Boot Redis Hands-On

A hands-on Spring Boot project demonstrating Redis integration for caching and in-memory data storage.

This project focuses on commonly used Redis concepts in Java and Spring Boot applications, with an emphasis on practical and interview-oriented learning.

---

## 🚀 Tech Stack

- Java 21
- Spring Boot 4.1
- Spring Data Redis
- Spring Data JPA
- MySQL
- Redis
- Maven
- Postman

---

## 📚 Topics Covered

### Redis Data Types

- ✅ Redis String
- ✅ Redis Hash

---

### Redis Features

- ✅ RedisTemplate
- ✅ TTL (Time To Live)
- ✅ EXPIRE
- ✅ PERSIST
- ✅ Cache Aside Pattern
- ✅ Cache Hit and Cache Miss
- ✅ Cache Invalidation

---

### Spring Boot Caching

- ✅ Spring Cache
- ✅ Redis CacheManager
- ✅ `@Cacheable`
- ✅ `@CachePut`
- ✅ `@CacheEvict`

---

## 📂 Project Structure

```text
src
├── controller
│   ├── ProductController
│   ├── RedisController
│   ├── ProductHashController
│   └── SpringCacheProductController
│
├── service
│   ├── ProductService
│   ├── RedisService
│   ├── ProductHashService
│   └── SpringCacheProductService
│
├── repository
│   └── ProductRepository
│
├── entity
│   └── Product
│
├── config
│   └── RedisConfig
│
└── resources
    └── application.yaml
```

---

## 🛠 Prerequisites

- Java 21
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

If using Docker:

```bash
docker run --name redis -p 6379:6379 -d redis
```

### Create MySQL Database

```sql
CREATE DATABASE redis_demo;
```

### Configure `application.yaml`

Update the MySQL and Redis connection details according to your local environment.

Example:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/redis_demo
    username: your_username
    password: your_password

  data:
    redis:
      host: localhost
      port: 6379
```

### Run the application

```bash
mvn spring-boot:run
```

---

## 🧪 Testing

The application was tested using:

- Postman
- Application logs
- `redis-cli`

Redis commands can be used to inspect stored keys, hashes, and TTL values.

---

## 📖 Learning Roadmap

- [x] Configure Redis with Spring Boot
- [x] Practice Redis String operations
- [x] Practice Redis Hash operations
- [x] Work with TTL
- [x] Work with EXPIRE and PERSIST
- [x] Implement Cache Aside Pattern
- [x] Understand Cache Hit and Cache Miss
- [x] Implement Cache Invalidation
- [x] Explore RedisTemplate
- [x] Implement Spring Cache
- [x] Implement `@Cacheable`
- [x] Implement `@CachePut`
- [x] Implement `@CacheEvict`

---

## 🎯 Goal

The objective of this repository is to gain practical experience with Redis and understand how it is commonly used with Spring Boot applications.

The project focuses on interview-relevant Redis concepts, including caching, Redis data structures, TTL management, cache invalidation, Cache Aside Pattern, and Spring Cache annotations.

The goal is to build a practical understanding of Redis for Java and Spring Boot development rather than cover advanced Redis concepts in depth.

---

## 📄 License

This project is created for learning and demonstration purposes.