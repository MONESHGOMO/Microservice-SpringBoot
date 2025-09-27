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
| **API Gateway** | 8070 | Routes requests to catalog or streaming services |
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
***

