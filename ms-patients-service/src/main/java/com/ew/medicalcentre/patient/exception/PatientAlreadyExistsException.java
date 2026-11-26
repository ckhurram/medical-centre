package com.ew.medicalcentre.patient.exception;

import lombok.Getter;

/**
 * Exception thrown when trying to register with an email that already exists.
 */
@Getter
public class PatientAlreadyExistsException extends RuntimeException {

    private final String email;

    public PatientAlreadyExistsException(String email) {
        super("Patient already exists with email: " + email);
        this.email = email;
    }
}