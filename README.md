# Movie Streaming & Catalog Microservices Application

## Overview

This is a Spring Boot microservices application for managing a movie catalog and streaming movies. It demonstrates microservice architecture using Spring Cloud with API Gateway routing and Eureka service discovery.

The application allows:
- Adding and listing movies with metadata (name, description, path)
- Streaming movies from a local folder via REST API

## Tech Stack

- **Backend**: Java 17, Spring Boot 3.5.6
- **Spring Cloud**: Gateway, Eureka Discovery, Load Balancer
- **Database**: MySQL 8
- **Build Tool**: Maven
- **Streaming**: REST API using InputStreamResource
- **Frontend (REST CLIENT)**: Postman

## Microservices Architecture

| Service | Port | Description |
|---------|------|-------------|
| **API Gateway** | 8000 | Routes requests to catalog or streaming services |
| **Eureka Server** | 8761 | Service discovery and registration |
| **Movie Catalog Service** | 8090 | Stores movie metadata in MySQL database |
| **Movie Streaming Service** | 8091 | Streams video files from local directory |

## API Endpoints

### API Gateway Routes

| Route | Method | Description | Forwarded To |
|-------|--------|-------------|--------------|
| `/movie-info/**` | GET/POST | Movie catalog operations | Movie Catalog Service |
| `/stream/**` | GET | Stream video by name | Movie Streaming Service |

### Movie Catalog Service

| Endpoint | Method | Request Body | Response | Description |
|----------|--------|--------------|----------|-------------|
| `/movie-info/save` | POST | JSON Array of movies | Saved movie list with generated id | Save multiple movies to catalog |
| `/movie-info/list` | GET | None | JSON Array of movies | Retrieve all movies from catalog |

#### Example Request to Save Movies:

```json
[
  {
    "name": "Inception",
    "description": "A mind-bending thriller",
    "path": "Inception.mp4"
  },
  {
    "name": "Interstellar",
    "description": "Space exploration epic",
    "path": "Interstellar.mp4"
  }
]

```
### **Movie Streaming Service**

| Endpoint | Method | Path Variable | Description |
|----------|--------|---------------|-------------|
| `/stream/{videoName}` | GET | `videoName` = name of file in `C:\Users\ASUS\Videos\` | Streams the video file |

**Example:**
```
GET http://localhost:8000/stream/Inception.mp4
```
→ Streams `C:\Users\ASUS\Videos\Inception.mp4`

## **Prerequisites**
- Java 23
- MySQL 8
- Maven
- Video files stored in `C:\Users\ASUS\Videos\`


## **Usage**

1. **List all movies:**
   ```bash
   curl http://localhost:8000/movie-info/list
   ```

2. **Stream a movie:**
   ``` bash
   http://localhost:8000/stream/Inception.mp4
   ```

## **Troubleshooting Common Issues**

### **404 Not Found on API Gateway**
- ✅ Ensure API Gateway routes are correct in `application.yml`
- ✅ Verify Movie Catalog service is running on port 8090
- ✅ Check Eureka dashboard at `http://localhost:8761` for registered services

### **Movie Not Found for Streaming**
- ✅ Ensure `videoName` matches exactly the filename in `C:\Users\ASUS\Videos\`
- ✅ Check file permissions in the videos directory
- ✅ Verify file extension case sensitivity (.mp4 vs .MP4)

### **Database Connection Issues**
- ✅ Ensure MySQL service is running
- ✅ Verify database `mcl` exists
- ✅ Check connection credentials in each service's configuration

### **Eureka Service Discovery Problems**
- ✅ All microservices must register with Eureka
- ✅ Verify each service appears in Eureka dashboard
- ✅ Check service names match in Eureka registration

## **Project Structure**