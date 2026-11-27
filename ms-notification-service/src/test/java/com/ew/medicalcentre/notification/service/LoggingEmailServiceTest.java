package com.ew.medicalcentre.notification.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ew.medicalcentre.notification.event.PatientRegisteredEvent;

class LoggingEmailServiceTest {

    @Test
    void sendWelcomeEmail_logsContent() {
        LoggingEmailService service = new LoggingEmailService();
        PatientRegisteredEvent event = new PatientRegisteredEvent(
                "patient-123",
                "jane@example.com",
                "Jane",
                "Doe",
                "1990-01-01",
                "+15559876543");

        // Should not throw
        assertDoesNotThrow(() -> service.sendWelcomeEmail(event));
    }

    @Test
    void sendWelcomeEmail_withNullValues() {
        LoggingEmailService service = new LoggingEmailService();
        PatientRegisteredEvent event = new PatientRegisteredEvent(
                null, null, null, null, null, null);

        assertDoesNotThrow(() -> service.sendWelcomeEmail(event));
    }
}