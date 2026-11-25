# Identity Service

> **Module**: `ms-identity-service`  
> **Package**: `com.ew.medicalcentre.identity`  
> **Port**: 8084

## Overview

Identity and access management microservice backed by Keycloak. Handles OAuth2/OIDC authentication, token introspection, and user session management. Services use Keycloak's resource server validation.

## Tech Stack

- Java 25, Spring Boot 3.4.0
- Spring Security + Keycloak BOM
- Spring Boot Starter OAuth2 Resource Server
- JWT Bearer Token Validation

## Build & Run

Keycloak must be running locally first:

```bash
cd ms-identity-service
../mvnw clean package -DskipTests
# Start Keycloak: docker-compose up keycloak
../mvnw spring-boot:run
```

## Configuration

See `src/main/resources/application.yml` for:
- Keycloak Auth Server URL
- Client registration
- Resource server settings

See `deploy/keycloak/realm-export.json` for realm configuration (patients + admins).