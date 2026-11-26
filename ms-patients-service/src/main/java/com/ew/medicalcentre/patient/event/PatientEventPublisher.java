package com.ew.medicalcentre.patient.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes PatientRegisteredEvent to Kafka topic "patient-events".
 */
@Component
public class PatientEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(PatientEventPublisher.class);
    private static final String TOPIC = "patient-events";

    private final KafkaTemplate<String, PatientRegisteredEvent> kafkaTemplate;

    public PatientEventPublisher(KafkaTemplate<String, PatientRegisteredEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPatientRegistered(PatientRegisteredEvent event) {
        log.info("Publishing PatientRegisteredEvent for patientId: {}", event.getPatientId());
        kafkaTemplate.send(TOPIC, event.getPatientId(), event);
    }
}