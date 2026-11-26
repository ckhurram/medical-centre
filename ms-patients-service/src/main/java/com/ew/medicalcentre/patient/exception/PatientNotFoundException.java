package com.ew.medicalcentre.patient.exception;

/**
 * Exception thrown when a patient is not found by ID.
 */
public class PatientNotFoundException extends RuntimeException {

    private final String patientId;

    public PatientNotFoundException(String patientId) {
        super("Patient not found: " + patientId);
        this.patientId = patientId;
    }

    public String getPatientId() { return patientId; }
}