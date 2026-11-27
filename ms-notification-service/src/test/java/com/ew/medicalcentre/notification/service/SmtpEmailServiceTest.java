package com.ew.medicalcentre.notification.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import jakarta.mail.internet.MimeMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import com.ew.medicalcentre.notification.event.PatientRegisteredEvent;

class SmtpEmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    private SmtpEmailService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        service = new SmtpEmailService(javaMailSender);
    }

    @Test
    void sendWelcomeEmail_invokesMailSender() {
        PatientRegisteredEvent event = new PatientRegisteredEvent(
                "patient-123",
                "jane@example.com",
                "Jane",
                "Doe",
                "1990-01-01",
                "+15559876543");

        service.sendWelcomeEmail(event);

        verify(javaMailSender).send(any(MimeMessage.class));
    }
}