# Feature Spec: Appointment Booking

## Overview
Patients can book appointments with available medics. The system verifies medic availability
and orchestrates the booking via a saga pattern. Confirmation and reminder notifications
are sent automatically.

## Actors
- **Patient** — books and manages appointments
- **Medic** — receives appointment bookings
- **System** — validates availability, orchestrates booking, sends notifications

## User Stories
- As a patient, I want to book an appointment with a medic during their available slot.
- As a patient, I want to receive a confirmation when my appointment is booked.
- As a patient, I want to receive a reminder before my upcoming appointment.
- As a system, I want to prevent double-booking of the same medic slot.

## Acceptance Criteria
1. Patient can view available medic slots
2. Patient can book an appointment (patient ID, medic ID, date/time slot)
3. System validates medic availability before confirming
4. On successful booking: appointment record created, `AppointmentBooked` event published
5. On conflict (slot already booked): 409 with descriptive message
6. Notification service sends booking confirmation and reminder emails
7. Booking requires PATIENT role

## API Endpoints
| Method | Path | Description | Auth |
|--------|------|-------------|------|
| POST | `/api/v1/appointments` | Book an appointment | PATIENT |
| GET | `/api/v1/appointments/{id}` | Get appointment details | PATIENT/ADMIN |
| PUT | `/api/v1/appointments/{id}` | Update/reschedule appointment | PATIENT |
| DELETE | `/api/v1/appointments/{id}` | Cancel appointment | PATIENT |
| GET | `/api/v1/appointments/patient/{id}` | List patient's appointments | PATIENT |

## Events Produced
| Event | Topic | Schema | Producer |
|-------|-------|--------|----------|
| `AppointmentBooked` | `appointment-events` | `appointment-booked.avsc` | Appointment Service |
| `AppointmentUpdated` | `appointment-events` | `appointment-updated.avsc` | Appointment Service |

## Events Consumed
| Event | Topic | Consumer |
|-------|-------|----------|
| `AppointmentBooked` | `appointment-events` | Notification Service |
| `AppointmentUpdated` | `appointment-events` | Notification Service |

## Dependencies
- MongoDB (appointment database)
- Kafka (for `appointment-events` topic)
- Medic Service (availability validation via events)
- Notification Service (booking confirmations, reminders)
