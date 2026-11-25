# ADR-003: Schema-First: OpenAPI + Avro

## Status
Proposed

## Date
2026-11-25

## Decision Makers
Architecture Team

## Context
REST APIs and Kafka events need well-defined contracts so frontend and backend teams,
and inter-service teams, can work in parallel without breaking each other.

## Decision
Use a schema-first approach:
- **REST APIs**: OpenAPI 3.x specifications in `shared/schemas/openapi/`
- **Kafka Events**: Avro schemas in `shared/schemas/avro/`
- Code generation from schemas where applicable (e.g., Avro Java classes)

Schema changes go through review before being merged.

## Consequences

### Positive
- Contract-driven development — frontend and backend teams work in parallel
- Breaking changes caught at schema review time
- Auto-generated client SDKs and server stubs
- Single source of truth for API contracts

### Negative
- Schema changes require coordination across services
- Additional tooling setup for code generation
- Slightly slower initial development (schema must be defined first)

## Alternatives Considered
- **Code-first (Spring REST Docs / Swagger annotations)**: Rejected — contract drift between services
- **Protobuf / gRPC**: Rejected — team more familiar with REST/OpenAPI, HTTP/1 sufficient for internal APIs