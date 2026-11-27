package com.ew.medicalcentre.patient.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * Event published when a patient is successfully registered.
 * Matches Avro schema: shared/schemas/avro/patient-registered.avsc
 *
 * Uses Lombok to generate constructors, getters and setters.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRegisteredEvent {

    private String patientId;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phone;
    private long occurredAt;

    /**
     * Convenience constructor that stamps occurredAt with the current time.
     */
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
}
