package com.ew.medicalcentre.patient.exception;

import lombok.Getter;

/**
 * Exception thrown when a patient is not found by ID.
 */
@Getter
public class PatientNotFoundException extends RuntimeException {

    private final String patientId;

    public PatientNotFoundException(String patientId) {
        super("Patient not found: " + patientId);
        this.patientId = patientId;
    }
}
