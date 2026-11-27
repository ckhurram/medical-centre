package com.ew.medicalcentre.patient.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PhoneNumberTest {

    @Test
    void validPhoneNumber_acceptsE164Format() {
        PhoneNumber phone = new PhoneNumber("+15551234567");
        assertEquals("+15551234567", phone.value());

        PhoneNumber phone2 = new PhoneNumber("+442079460000");
        assertEquals("+442079460000", phone2.value());
    }

    @Test
    void invalidPhoneNumber_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("abc"));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(""));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(null));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("+0123"));
    }
}