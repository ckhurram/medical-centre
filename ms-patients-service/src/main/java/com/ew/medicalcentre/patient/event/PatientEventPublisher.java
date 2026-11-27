package com.ew.medicalcentre.patient.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes PatientRegisteredEvent to Kafka topic "patient-events".
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PatientEventPublisher {

    private static final String TOPIC = "patient-events";

    private final KafkaTemplate<String, PatientRegisteredEvent> kafkaTemplate;

    public void publishPatientRegistered(PatientRegisteredEvent event) {
        log.info("Publishing PatientRegisteredEvent for patientId: {}", event.getPatientId());
        kafkaTemplate.send(TOPIC, event.getPatientId(), event);
    }
}