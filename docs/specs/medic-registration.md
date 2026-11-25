# Feature Spec: Medic Registration

## Overview
Administrators can register medic/doctor details including specialization and availability
schedule. Medics are then available for patients to book appointments against.

## Actors
- **Admin** — registers medics and manages availability
- **System** — validates input, creates medic record, publishes availability updates

## User Stories
- As an admin, I want to register a medic with their details and specialization so patients can book with them.
- As an admin, I want to set a medic's availability schedule so appointments can only be booked during available slots.
- As a system, I want to publish `MedicAvailabilityUpdated` events when availability changes.

## Acceptance Criteria
1. Admin can submit medic registration with: name, email, specialization, qualification, phone
2. Admin can set weekly availability (day of week, start time, end time)
3. Email must be unique — duplicate medic registration rejected
4. On availability change: `MedicAvailabilityUpdated` event published to Kafka
5. Appointment Service consumes availability events to validate booking slots
6. Invalid input returns 400 with descriptive error messages
7. Medic registration requires ADMIN role

## API Endpoints
| Method | Path | Description | Auth |
|--------|------|-------------|------|
| POST | `/api/v1/medics` | Register a new medic | ADMIN |
| GET | `/api/v1/medics/{id}` | Get medic details | PATIENT/ADMIN |
| PUT | `/api/v1/medics/{id}/availability` | Set medic availability | ADMIN |
| GET | `/api/v1/medics/{id}/availability` | Get medic availability | PATIENT/ADMIN |

## Events Produced
| Event | Topic | Schema | Producer |
|-------|-------|--------|----------|
| `MedicAvailabilityUpdated` | `medic-events` | `medic-availability-updated.avsc` | Medic Service |

## Events Consumed
| Event | Topic | Consumer |
|-------|-------|----------|
| (none) | | |

## Dependencies
- MongoDB (medic database)
- Kafka (for `medic-events` topic)
