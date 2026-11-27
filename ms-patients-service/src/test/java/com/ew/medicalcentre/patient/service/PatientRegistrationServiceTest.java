package com.ew.medicalcentre.patient.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ew.medicalcentre.patient.dto.PatientProfileResponse;
import com.ew.medicalcentre.patient.dto.PatientRegistrationRequest;
import com.ew.medicalcentre.patient.event.PatientEventPublisher;
import com.ew.medicalcentre.patient.event.PatientRegisteredEvent;
import com.ew.medicalcentre.patient.exception.PatientAlreadyExistsException;
import com.ew.medicalcentre.patient.model.Patient;
import com.ew.medicalcentre.patient.repository.PatientRepository;

class PatientRegistrationServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientEventPublisher patientEventPublisher;

    private PatientRegistrationService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new PatientRegistrationService(patientRepository, patientEventPublisher);
    }

    private PatientRegistrationRequest validRequest() {
        return new PatientRegistrationRequest(
                "john.doe@example.com",
                "John",
                "Doe",
                "1990-05-15",
                "+15551234567");
    }

    @Test
    @DisplayName("Register patient success - persists and publishes event")
    void registerPatient_success() {
        // Arrange
        PatientRegistrationRequest request = validRequest();
        when(patientRepository.existsByEmail("john.doe@example.com")).thenReturn(false);

        Patient savedPatient = new Patient(
                "patient-123",
                "john.doe@example.com",
                "John",
                "Doe",
                LocalDate.of(1990, 5, 15),
                "+15551234567",
                LocalDateTime.now(),
                LocalDateTime.now());
        when(patientRepository.save(any(Patient.class))).thenReturn(savedPatient);

        // Act
        PatientProfileResponse response = service.registerPatient(request);

        // Assert
        assertNotNull(response);
        assertEquals("patient-123", response.patientId());
        assertEquals("john.doe@example.com", response.email());
        assertEquals("John", response.firstName());
        assertEquals("Doe", response.lastName());

        // Verify event published with correct patientId
        ArgumentCaptor<PatientRegisteredEvent> captor =
                ArgumentCaptor.forClass(PatientRegisteredEvent.class);
        verify(patientEventPublisher).publishPatientRegistered(captor.capture());
        assertEquals("patient-123", captor.getValue().getPatientId());
        assertEquals("john.doe@example.com", captor.getValue().getEmail());
    }

    @Test
    @DisplayName("Register patient with duplicate email throws exception")
    void registerPatient_duplicateEmail() {
        // Arrange
        PatientRegistrationRequest request = validRequest();
        when(patientRepository.existsByEmail("john.doe@example.com")).thenReturn(true);

        // Act & Assert
        PatientAlreadyExistsException ex = assertThrows(
                PatientAlreadyExistsException.class,
                () -> service.registerPatient(request));
        assertEquals("Patient already exists with email: john.doe@example.com", ex.getMessage());

        // Verify nothing saved or published
        verify(patientRepository, never()).save(any());
        verify(patientEventPublisher, never()).publishPatientRegistered(any());
    }

    @Test
    @DisplayName("Register patient with null dateOfBirth and phone maps to empty strings")
    void registerPatient_nullOptionalFields() {
        PatientRegistrationRequest request = new PatientRegistrationRequest(
                "minimal@example.com", "Min", "Imal", "1990-05-15", null);
        when(patientRepository.existsByEmail("minimal@example.com")).thenReturn(false);

        // Saved patient with null dateOfBirth and phone to exercise false branches
        Patient savedPatient = new Patient(
                "patient-999", "minimal@example.com", "Min", "Imal",
                null, null, LocalDateTime.now(), LocalDateTime.now());
        when(patientRepository.save(any(Patient.class))).thenReturn(savedPatient);

        PatientProfileResponse response = service.registerPatient(request);

        assertEquals("", response.dateOfBirth());
        assertEquals("", response.phone());
    }
}