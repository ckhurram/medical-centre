# ADR-001: Domain-Driven Design

## Status
Proposed

## Date
2026-11-25

## Decision Makers
Architecture Team

## Context
The Medical Centre Appointment Booking System needs to model distinct business domains:
- Patient registration and profile management
- Medic/doctor management with availability
- Appointment booking and lifecycle
- Event-driven notifications

A shared model across all concerns would become a "big ball of mud" with unclear boundaries,
making the system hard to maintain and evolve.

## Decision
Use Domain-Driven Design with four bounded contexts:
1. **Patient Context** — patient registration, profile, demographics
2. **Medic Context** — medic details, specializations, availability slots
3. **Appointment Context** — booking, scheduling, cancellation, rescheduling
4. **Notification Context** — email/SMS notification dispatch

Each bounded context has its own microservice, own MongoDB database, and own domain model.

## Consequences

### Positive
- Clear domain boundaries and ownership
- Each team can own and evolve their service independently
- Ubiquitous language within each context reduces ambiguity
- Services can be scaled independently based on load

### Negative
- Distributed transactions require saga patterns (eventual consistency)
- Data duplication across services
- Cross-context queries require API gateway composition or CQRS

## Alternatives Considered
- **Single monolith**: Rejected — would not allow independent deployment/scaling
- **Service-oriented architecture (SOA)**: Rejected — too much shared infrastructure coupling