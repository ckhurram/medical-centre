# ADR-002: Event-Driven Architecture with Kafka

## Status
Proposed

## Date
2026-11-25

## Decision Makers
Architecture Team

## Context
Domain services need to communicate asynchronously. For example:
- When a patient registers, the notification service should send a welcome email
- When an appointment is booked, the medic availability should be updated
- When an appointment is upcoming, reminders should be sent

Synchronous REST calls between services would create tight coupling and cascading failures.

## Decision
Use Apache Kafka as the event backbone. Domain services produce events to Kafka topics,
and interested services consume them asynchronously.

Key design decisions:
- **Event schemas**: Avro for type safety and evolution
- **Event topics**: one per domain event type (e.g., `patient-events`, `appointment-booked`)
- **Consumer groups**: each service has its own consumer group
- **Delivery semantics**: at-least-once with idempotent consumers

## Consequences

### Positive
- Loose coupling between services
- Services can evolve independently
- Resilience: if a consumer is down, events are retained and replayed
- Natural audit trail of all domain events
- Supports eventual consistency for distributed transactions

### Negative
- Eventual consistency (not immediate)
- Additional operational complexity (Kafka cluster)
- Schema evolution management required
- Debugging distributed event flows is harder

## Alternatives Considered
- **Synchronous REST**: Rejected — tight coupling, cascading failures
- **RabbitMQ**: Rejected — Kafka better fits event sourcing and replay use cases
- **AWS EventBridge**: Rejected — want self-hosted, cloud-agnostic solution