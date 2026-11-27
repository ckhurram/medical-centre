package com.ew.medicalcentre.patient.model;

/**
 * Value object for email validation.
 * Used both as a domain entity and for API request validation.
 */
public record Email(String value) {

    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
    }
}
