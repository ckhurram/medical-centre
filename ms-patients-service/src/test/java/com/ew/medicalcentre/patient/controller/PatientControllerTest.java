package com.ew.medicalcentre.patient.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ew.medicalcentre.patient.dto.PatientProfileResponse;
import com.ew.medicalcentre.patient.dto.PatientRegistrationRequest;
import com.ew.medicalcentre.patient.service.PatientQueryService;
import com.ew.medicalcentre.patient.service.PatientRegistrationService;

class PatientControllerTest {

    @Mock
    private PatientRegistrationService patientRegistrationService;

    @Mock
    private PatientQueryService patientQueryService;

    private PatientController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new PatientController(patientRegistrationService, patientQueryService);
    }

    @Test
    void registerPatient_returnsCreated() {
        PatientRegistrationRequest request = new PatientRegistrationRequest(
                "john.doe@example.com", "John", "Doe", "1990-05-15", "+15551234567");
        PatientProfileResponse response = new PatientProfileResponse(
                "patient-123", "john.doe@example.com", "John", "Doe",
                "1990-05-15", "+15551234567", java.time.LocalDateTime.now());

        when(patientRegistrationService.registerPatient(any(PatientRegistrationRequest.class)))
            .thenReturn(response);

        ResponseEntity<PatientProfileResponse> result = controller.registerPatient(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("patient-123", result.getBody().patientId());
    }

    @Test
    void getPatientProfile_returnsPatient() {
        PatientProfileResponse response = new PatientProfileResponse(
                "patient-123", "john.doe@example.com", "John", "Doe",
                "1990-05-15", "+15551234567", java.time.LocalDateTime.now());

        when(patientQueryService.getPatientById("patient-123")).thenReturn(response);

        ResponseEntity<PatientProfileResponse> result = controller.getPatientProfile("patient-123");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("patient-123", result.getBody().patientId());
    }
}