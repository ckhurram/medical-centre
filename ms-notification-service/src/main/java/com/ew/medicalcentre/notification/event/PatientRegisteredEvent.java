package com.ew.medicalcentre.notification.event;

import java.time.Instant;

/**
 * Event consumed when a patient is registered.
 * Matches Avro schema: shared/schemas/avro/patient-registered.avsc
 */
public class PatientRegisteredEvent {

    private String patientId;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phone;
    private long occurredAt;

    public PatientRegisteredEvent() {
        this.occurredAt = Instant.now().toEpochMilli();
    }

    public PatientRegisteredEvent(String patientId, String email, String firstName,
                                  String lastName, String dateOfBirth, String phone) {
        this.patientId = patientId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.occurredAt = Instant.now().toEpochMilli();
    }

    // Getters and Setters
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public long getOccurredAt() { return occurredAt; }
    public void setOccurredAt(long occurredAt) { this.occurredAt = occurredAt; }
}