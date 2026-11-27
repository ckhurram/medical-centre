package com.ew.medicalcentre.patient.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PatientRegisteredEventTest {

    @Test
    void constructor_setsFields() {
        PatientRegisteredEvent event = new PatientRegisteredEvent(
                "patient-123", "jane@example.com", "Jane", "Doe", "1990-01-01", "+15551234567");

        assertEquals("patient-123", event.getPatientId());
        assertEquals("jane@example.com", event.getEmail());
        assertEquals("Jane", event.getFirstName());
        assertEquals("Doe", event.getLastName());
        assertEquals("1990-01-01", event.getDateOfBirth());
        assertEquals("+15551234567", event.getPhone());
        assertTrue(event.getOccurredAt() > 0);
    }

    @Test
    void setters_updateFields() {
        PatientRegisteredEvent event = new PatientRegisteredEvent();
        event.setPatientId("p1");
        event.setEmail("a@e.com");
        event.setFirstName("A");
        event.setLastName("B");
        event.setDateOfBirth("2000-01-01");
        event.setPhone("+1111111111");
        event.setOccurredAt(12345L);

        assertEquals("p1", event.getPatientId());
        assertEquals("a@e.com", event.getEmail());
        assertEquals("A", event.getFirstName());
        assertEquals("B", event.getLastName());
        assertEquals("2000-01-01", event.getDateOfBirth());
        assertEquals("+1111111111", event.getPhone());
        assertEquals(12345L, event.getOccurredAt());
    }
}