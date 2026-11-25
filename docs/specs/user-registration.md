# Feature Spec: User Registration

## Overview
Patients can register for the Medical Centre system by providing their email address
and completing a registration form. Upon successful registration, a welcome notification
is sent.

## Actors
- **Patient** — registers and creates a profile
- **System** — validates input, creates patient record, sends welcome notification

## User Stories
- As a patient, I want to register with my email and personal details so that I can book appointments.
- As a system, I want to send a welcome email after successful registration so the patient knows their account is active.

## Acceptance Criteria
1. Patient can submit a registration form with: email, first name, last name, date of birth, phone
2. Email must be unique — duplicate registration rejected
3. On successful registration: patient record created in MongoDB, `PatientRegistered` event published to Kafka
4. Notification service consumes `PatientRegistered` and sends welcome email
5. Invalid input returns 400 with descriptive error messages
6. Registration endpoint requires no authentication (public)

## API Endpoints
| Method | Path | Description | Auth |
|--------|------|-------------|------|
| POST | `/api/v1/patients` | Register a new patient | None |

## Events Produced
| Event | Topic | Schema | Producer |
|-------|-------|--------|----------|
| `PatientRegistered` | `patient-events` | `patient-registered.avsc` | Patient Service |

## Events Consumed
| Event | Topic | Consumer |
|-------|-------|----------|
| (none) | | |

## Dependencies
- MongoDB (patient database)
- Kafka (for `patient-events` topic)
- Notification Service (consumes `PatientRegistered`)
