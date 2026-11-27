package com.ew.medicalcentre.notification.consumer;

import com.ew.medicalcentre.notification.event.PatientRegisteredEvent;
import com.ew.medicalcentre.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consumes PatientRegisteredEvent from Kafka topic "patient-events"
 * and triggers the welcome email.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PatientEventConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "patient-events", groupId = "notification-service")
    public void onPatientRegistered(PatientRegisteredEvent event) {
        log.info("Received PatientRegisteredEvent for patientId: {}", event.getPatientId());
        emailService.sendWelcomeEmail(event);
        log.info("Processed welcome email for patient: {}", event.getEmail());
    }
}
