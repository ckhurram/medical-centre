package com.ew.medicalcentre.patient.model;

import jakarta.validation.constraints.Pattern;

/**
 * Value object representing an E.164 phone number.
 */
public class PhoneNumber {

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be in E.164 format")
    private final String value;

    public PhoneNumber(String value) {
        if (value == null || !value.matches("^\\+?[1-9]\\d{1,14}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.value = value;
    }

    public String getValue() { return value; }
    @Override
    public String toString() { return value; }
    @Override
    public boolean equals(Object o) { return o instanceof PhoneNumber && value.equals(((PhoneNumber) o).value); }
    @Override
    public int hashCode() { return value.hashCode(); }
}