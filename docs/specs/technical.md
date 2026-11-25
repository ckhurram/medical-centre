# Technical Design

## Service Communication
- **Synchronous**: REST via API Gateway (Spring Cloud Gateway)
- **Asynchronous**: Kafka events (Avro schemas)

## Data Persistence
- Each service owns a dedicated MongoDB database
- Spring Data MongoDB for data access

## Authentication
- Keycloak OAuth2/OIDC resource server
- JWT validation on each service
- Roles: PATIENT, ADMIN

## Error Handling
- Global exception handler per service
- RFC 7807 Problem Details for HTTP APIs
- Kafka dead-letter topic for failed event processing

## Observability
- Spring Boot Actuator endpoints
- Micrometer metrics exported to Prometheus
- OpenTelemetry tracing exported to Jaeger
- Structured logging

## Deployment
- Docker containers for all services
- Docker Compose for local orchestration
- Kubernetes manifests optional (in deploy/k8s/)
