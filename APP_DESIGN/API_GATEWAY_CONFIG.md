# Spring Cloud Gateway Configuration Documentation

This document explains the key configuration properties used in the `api-gateway` Spring Cloud Gateway application, their purpose, and which properties are mandatory for correct operation.

---

## Application Basic Settings

### `spring.application.name=api-gateway`
- **Purpose:** Defines the application name used for service registration with Eureka.
- **Mandatory:** Yes, needed for Eureka client to register and be discovered by other services.
- **Effect if missing:** The service may fail to register properly in Eureka, breaking discovery.

### `server.port=8070`
- **Purpose:** Defines the port where the gateway service will listen for incoming requests.
- **Mandatory:** No, defaults to `8080` if not specified. But usually set explicitly to avoid conflicts.

---

## Eureka Client Configuration

### `eureka.client.service-url.defaultZone=http://localhost:8761/eureka`
- **Purpose:** The URL of the Eureka server where the gateway registers and fetches registry.
- **Mandatory:** Yes, without this, Eureka discovery and registration will not work.

### `eureka.client.register-with-eureka=true`
- **Purpose:** Allows the gateway to register itself as a Eureka client service.
- **Mandatory:** Yes, if you want the gateway to be discoverable by other services.

### `eureka.client.fetch-registry=true`
- **Purpose:** Enables the gateway to fetch the list of registered services from Eureka.
- **Mandatory:** Yes, required for service discovery to work enabling routing to backend services.

---

## Spring Cloud Gateway Discovery Locator

### `spring.cloud.gateway.server.webflux.discovery.locator.enabled=true`
- **Purpose:** Enables automatic creation of gateway routes from services registered in Eureka.
- **Mandatory:** Optional but highly recommended to avoid manual route configuration for each service.

### `spring.cloud.gateway.server.webflux.discovery.locator.lower-case-service-id=true`
- **Purpose:** Converts discovered service IDs to lowercase for consistent routing.
- **Mandatory:** Optional, but helps prevent case sensitivity issues during routing.

---

## Routes Configuration

### Route 0: Movie Catalog Service

```properties
spring.cloud.gateway.server.webflux.routes.id=movie-catalog-service
spring.cloud.gateway.server.webflux.routes.predicates=Path=/movie-info/**
spring.cloud.gateway.server.webflux.routes.uri=lb://MOVIE-CATALOG-SERVICE
spring.cloud.gateway.server.webflux.routes.filters=StripPrefix=1
```

- **Purpose:**
    - `id`: Unique identifier for route management.
    - `predicates`: Matches incoming requests with path `/movie-info/**`.
    - `uri`: Specifies forwarding destination using load balancer prefix `lb://` for Eureka-based service discovery.
    - `filters`: `StripPrefix=1` removes `/movie-info` from the path before forwarding to backend service.
- **Mandatory:**
    - `id`, `predicates`, and `uri` are **mandatory** for route definition.
    - `filters` are optional, used here to simplify backend path handling.

### Route 1: Movie Streaming Service

```properties
spring.cloud.gateway.server.webflux.routes.id=movie-streaming-service
spring.cloud.gateway.server.webflux.routes.predicates=Path=/stream/**
spring.cloud.gateway.server.webflux.routes.uri=lb://MOVIE-STREAMING-SERVICE
spring.cloud.gateway.server.webflux.routes.filters=StripPrefix=1
```

- Same purpose and mandatory requirements as Route 0, adapted for the `MOVIE-STREAMING-SERVICE`.
- `StripPrefix=1` ensures the backend service receives the path without `/stream` prefix.

---

## Logging Configuration

```properties
logging.level.org.springframework.cloud.gateway=DEBUG
logging.level.org.springframework.cloud.loadbalancer=DEBUG
logging.level.reactor.netty.http.client.HttpClient=DEBUG
```

- **Purpose:** Enables detailed debug logging for troubleshooting gateway routing, load balancer actions, and HTTP client interactions.
- **Mandatory:** No, useful for development and debugging but can be set to `INFO` or `WARN` in production.

---

## Summary of Mandatory Properties

| Property/Setting                                              | Mandatory? | Reason                                         |
|--------------------------------------------------------------|------------|------------------------------------------------|
| `spring.application.name`                                    | Yes        | Service registration and discovery             |
| `server.port`                                                | No         | Defaults to 8080 but recommended to set explicitly |
| Eureka client properties (`service-url`, `register`, `fetch-registry`) | Yes        | Eureka registration and discovery              |
| `spring.cloud.gateway.server.webflux.routes[x].id`           | Yes        | Route identification                           |
| `spring.cloud.gateway.server.webflux.routes[x].predicates`   | Yes        | Defines which requests route matches           |
| `spring.cloud.gateway.server.webflux.routes[x].uri`          | Yes        | Destination backend URI                        |
| Route filters (e.g., `StripPrefix`)                          | No         | Depends on backend path expectations           |
| Discovery locator enabled                                    | No         | Enables dynamic route generation               |
| Logging levels                                               | No         | Optional for debugging                         |

---

This configuration enables a scalable API Gateway with Eureka-based service discovery and path prefix stripping for clean backend microservice routing.