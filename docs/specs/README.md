# Spec Driven Development

Documentation for spec-driven development (SDD) in this project.

## What is SDD?

Spec-Driven Development means writing the specification (what the feature does,
its API, its events, its acceptance criteria) *before* writing implementation code.
The specification serves as the contract between product, design, and engineering.

## Process

1. **Draft spec** — Write the feature specification
2. **Review spec** — Stakeholders review and approve
3. **Implement** — Code against the approved spec
4. **Verify** — Tests validate against spec acceptance criteria
5. **Update spec** — If implementation diverges, update the spec

## Living Documents

Specs are living documents — they evolve with the system. Every change to behavior
must be reflected in the spec before or alongside the code change.

## Spec Locations

- `docs/specs/functional.md` — High-level functional requirements
- `docs/specs/technical.md` — Technical architecture and design
- `docs/specs/events.md` — Kafka event specifications
- `docs/specs/domain-model.md` — Domain model (aggregates, entities, value objects)
- `docs/specs/user-registration.md` — User Registration feature spec
- `docs/specs/medic-registration.md` — Medic Registration feature spec
- `docs/specs/appointment-booking.md` — Appointment Booking feature spec
