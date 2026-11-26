package com.ew.medicalcentre.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception thrown when trying to register with an email that already exists.
 */
public class PatientAlreadyExistsException extends RuntimeException {

    private final String email;

    public PatientAlreadyExistsException(String email) {
        super("Patient already exists with email: " + email);
        this.email = email;
    }

    public String getEmail() { return email; }
}