# Event Specifications

## Topics

| Topic | Description | Producer | Consumer(s) |
|-------|-------------|----------|-------------|
| `patient-events` | Patient lifecycle events | Patient Service | Notification Service |
| `appointment-events` | Appointment lifecycle events | Appointment Service | Notification Service |
| `medic-events` | Medic availability events | Medic Service | Appointment Service |

## Event Schema (Avro)

Avro schemas live in `shared/schemas/avro/`:
- `patient-registered.avsc`
- `appointment-booked.avsc`
- `appointment-updated.avsc`
- `appointment-reminder.avsc`
- `medic-availability-updated.avsc`

## Event Format

Each Kafka message uses:
- **Key**: entity ID (String)
- **Value**: Avro-serialized event payload
- **Headers**: event type, timestamp, correlation ID, causation ID

## Consumer Groups

| Service | Consumer Group | Topics |
|---------|---------------|--------|
| Notification Service | `notification-service` | `patient-events`, `appointment-events` |
| Appointment Service | `appointments-service` | `medic-events` |
