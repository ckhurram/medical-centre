package com.ew.medicalcentre.patient.event;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

class PatientEventPublisherTest {

    @Mock
    private KafkaTemplate<String, PatientRegisteredEvent> kafkaTemplate;

    private PatientEventPublisher publisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        publisher = new PatientEventPublisher(kafkaTemplate);
    }

    @Test
    void publishPatient_registersEvent() {
        PatientRegisteredEvent event = new PatientRegisteredEvent(
                "patient-123", "jane@example.com", "Jane", "Doe", "1990-01-01", "+15551234567");

        publisher.publishPatientRegistered(event);

        verify(kafkaTemplate).send(anyString(), anyString(), any(PatientRegisteredEvent.class));
    }
}