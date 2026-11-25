# Domain Model

## Patient Context

**Aggregate Root**: `Patient`
- patientId (UUID)
- email (Email, unique)
- firstName
- lastName
- dateOfBirth
- phone
- createdAt, updatedAt

**Value Objects**: Email, PhoneNumber

## Medic Context

**Aggregate Root**: `Medic`
- medicId (UUID)
- email (Email, unique)
- firstName, lastName
- specialization
- qualification
- phone
- availability (List<AvailabilitySlot>)

**Value Objects**: Email, PhoneNumber, AvailabilitySlot (dayOfWeek, startTime, endTime)

## Appointment Context

**Aggregate Root**: `Appointment`
- appointmentId (UUID)
- patientId (UUID, reference)
- medicId (UUID, reference)
- appointmentDateTime
- status (PENDING, CONFIRMED, CANCELLED, COMPLETED)
- createdAt, updatedAt

**Value Objects**: AppointmentStatus

## Notification Context

**Entity**: `Notification`
- notificationId (UUID)
- recipient (Email)
- type (WELCOME, BOOKING_CONFIRMATION, REMINDER)
- status (PENDING, SENT, FAILED)
- sentAt

## Cross-Context References
- Appointment.patientId → Patient.patientId
- Appointment.medicId → Medic.medicId
