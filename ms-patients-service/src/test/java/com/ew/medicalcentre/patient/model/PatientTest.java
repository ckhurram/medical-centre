package com.ew.medicalcentre.patient.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PatientTest {

    @Test
    void patient_withAllFields() {
        Patient p = new Patient(
                "p1", "a@b.com", "John", "Doe",
                java.time.LocalDate.of(1990, 1, 1), "+15551234567",
                java.time.LocalDateTime.now(), java.time.LocalDateTime.now());
        assertEquals("p1", p.patientId());
        assertEquals("a@b.com", p.email());
    }

    @Test
    void patient_autoGeneratesPatientId() {
        Patient p = new Patient(
                null, "a@b.com", "John", "Doe",
                null, null, null, null);
        assertNotNull(p.patientId());
    }

    @Test
    void patient_autoGeneratesCreatedAt() {
        Patient p = new Patient(
                "p1", "a@b.com", "John", "Doe",
                null, null, null, null);
        assertNotNull(p.createdAt());
    }

    @Test
    void patient_autoGeneratesUpdatedAt() {
        Patient p = new Patient(
                "p1", "a@b.com", "John", "Doe",
                null, null, null, null);
        assertNotNull(p.updatedAt());
    }
}