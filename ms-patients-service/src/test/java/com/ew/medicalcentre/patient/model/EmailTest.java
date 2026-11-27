package com.ew.medicalcentre.patient.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailTest {

    @Test
    void validEmail() {
        Email email = new Email("john.doe@example.com");
        assertEquals("john.doe@example.com", email.value());
    }

    @Test
    void nullEmail_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Email(null));
    }

    @Test
    void blankEmail_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Email("   "));
    }
}