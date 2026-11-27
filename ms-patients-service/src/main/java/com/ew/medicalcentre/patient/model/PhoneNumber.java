package com.ew.medicalcentre.patient.model;

/**
 * Value object representing an E.164 phone number.
 * Implemented as a Java record — immutable with auto-generated accessors.
 */
public record PhoneNumber(String value) {

    public PhoneNumber {
        if (value == null || !value.matches("^\\+?[1-9]\\d{1,14}$")) {
            throw new IllegalArgumentException("Invalid phone number format: " + value);
        }
    }
}
