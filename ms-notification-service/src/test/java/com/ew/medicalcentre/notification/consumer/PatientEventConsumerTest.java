package com.ew.medicalcentre.notification.consumer;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ew.medicalcentre.notification.event.PatientRegisteredEvent;
import com.ew.medicalcentre.notification.service.EmailService;

class PatientEventConsumerTest {

    @Mock
    private EmailService emailService;

    private PatientEventConsumer consumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consumer = new PatientEventConsumer(emailService);
    }

    @Test
    void onPatientRegistered_invokesEmailService() {
        PatientRegisteredEvent event = new PatientRegisteredEvent(
                "patient-123",
                "jane@example.com",
                "Jane",
                "Doe",
                "1990-01-01",
                "+15559876543");

        consumer.onPatientRegistered(event);

        verify(emailService, times(1)).sendWelcomeEmail(event);
    }

    @Test
    void onPatientRegistered_withDifferentEvent() {
        PatientRegisteredEvent event = new PatientRegisteredEvent(
                "patient-456",
                "john@example.com",
                "John",
                "Smith",
                "1985-06-15",
                "+15551112222");

        consumer.onPatientRegistered(event);

        verify(emailService).sendWelcomeEmail(event);
        verifyNoMoreInteractions(emailService);
    }
}