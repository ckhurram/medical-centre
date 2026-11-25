# Functional Requirements

## Patient Registration
- FR-001: Patients can register with email, name, DOB, phone
- FR-002: Email uniqueness enforced
- FR-003: Welcome email sent on registration

## Medic Management
- FR-004: Admins can register medics with specialization
- FR-005: Admins can set medic availability schedule
- FR-006: Medics can be searched by specialization

## Appointment Booking
- FR-007: Patients can book appointments during available slots
- FR-008: System prevents double-booking
- FR-009: Patients can view their appointments
- FR-010: Patients can cancel/reschedule appointments

## Notifications
- FR-011: Booking confirmation email sent
- FR-012: Appointment reminder sent before appointment
- FR-013: Welcome email sent on registration

## Authentication
- FR-014: Patients authenticate via Keycloak
- FR-015: Admins authenticate via Keycloak with ADMIN role
- FR-016: JWT-based stateless authentication
