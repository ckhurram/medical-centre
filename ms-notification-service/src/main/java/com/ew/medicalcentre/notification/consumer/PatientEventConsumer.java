package com.ew.medicalcentre.notification.consumer;

import com.ew.medicalcentre.notification.event.PatientRegisteredEvent;
import com.ew.medicalcentre.notification.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consumes PatientRegisteredEvent from Kafka topic "patient-events"
 * and triggers the welcome email.
 */
@Component
public class PatientEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(PatientEventConsumer.class);

    private final EmailService emailService;

    public PatientEventConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "patient-events", groupId = "notification-service")
    public void onPatientRegistered(PatientRegisteredEvent event) {
        log.info("Received PatientRegisteredEvent for patientId: {}", event.getPatientId());
        emailService.sendWelcomeEmail(event);
        log.info("Processed welcome email for patient: {}", event.getEmail());
    }
}