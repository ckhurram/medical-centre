package com.ew.medicalcentre.patient.model;

/**
 * Value object for email validation.
 * Used both as a domain entity and for API request validation.
 */
public class Email {

    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        this.value = value;
    }

    public String getValue() { return value; }
    @Override
    public String toString() { return value; }
    @Override
    public boolean equals(Object o) { return o instanceof Email && value.equals(((Email) o).value); }
    @Override
    public int hashCode() { return value.hashCode(); }
}