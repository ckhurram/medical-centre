# ADR-004: MongoDB Per Service

## Status
Proposed

## Date
2026-11-25

## Decision Makers
Architecture Team

## Context
Each bounded context needs data persistence. Sharing a single database schema across
services would create tight coupling and make independent deployment impossible.

## Decision
Each microservice owns its own MongoDB database:
- `medical-centre-patients` — Patient Service
- `medical-centre-medics` — Medic Service
- `medical-centre-appointments` — Appointment Service
- `medical-centre-notifications` — Notification Service

No direct cross-service database access. Inter-service communication happens via:
- REST through the API Gateway (synchronous)
- Kafka events (asynchronous)

## Consequences

### Positive
- Service autonomy — each team owns their schema
- Independent scaling and deployment
- No database-level coupling between services

### Negative
- Data duplication (e.g., patient ID referenced in appointments and medic services)
- No ACID transactions across services — saga pattern required
- Cross-service queries require API composition

## Alternatives Considered
- **Shared database**: Rejected — tight coupling, schema conflicts
- **PostgreSQL per service**: Rejected — MongoDB fits document-oriented domain models better