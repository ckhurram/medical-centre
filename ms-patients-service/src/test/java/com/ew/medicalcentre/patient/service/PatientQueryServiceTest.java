package com.ew.medicalcentre.patient.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.ew.medicalcentre.patient.dto.PatientProfileResponse;
import com.ew.medicalcentre.patient.model.Patient;
import com.ew.medicalcentre.patient.repository.PatientRepository;
import com.ew.medicalcentre.patient.exception.PatientNotFoundException;

class PatientQueryServiceTest {

    @Mock
    private PatientRepository patientRepository;

    private PatientQueryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new PatientQueryService(patientRepository);
    }

    @Test
    void getPatientById_found() {
        Patient patient = new Patient(
                "patient-123",
                "john.doe@example.com",
                "John",
                "Doe",
                LocalDate.of(1990, 5, 15),
                "+15551234567",
                LocalDateTime.now(),
                LocalDateTime.now());

        when(patientRepository.findById("patient-123")).thenReturn(Optional.of(patient));

        PatientProfileResponse response = service.getPatientById("patient-123");

        assertNotNull(response);
        assertEquals("patient-123", response.patientId());
        assertEquals("john.doe@example.com", response.email());
        assertEquals("John", response.firstName());
        assertEquals("Doe", response.lastName());
    }

    @Test
    void getPatientById_notFound() {
        when(patientRepository.findById("nonexistent")).thenReturn(Optional.empty());

        PatientNotFoundException ex = assertThrows(
                PatientNotFoundException.class,
                () -> service.getPatientById("nonexistent"));

        assertEquals("nonexistent", ex.getPatientId());
    }

    @Test
    void getPatientById_withNullFields() {
        Patient patient = new Patient(
                "patient-123",
                "john.doe@example.com",
                "John",
                "Doe",
                null,
                null,
                LocalDateTime.now(),
                LocalDateTime.now());
        when(patientRepository.findById("patient-123")).thenReturn(Optional.of(patient));
        PatientProfileResponse response = service.getPatientById("patient-123");
        assertEquals("", response.dateOfBirth());
        assertEquals("", response.phone());
    }

    @Test
    void getPatients_pageable() {
        List<Patient> patients = List.of(
                new Patient("p1", "a@e.com", "A", "One", LocalDate.of(1990, 1, 1), "+1111111111", LocalDateTime.now(), LocalDateTime.now()),
                new Patient("p2", "b@e.com", "B", "Two", LocalDate.of(1991, 2, 2), "+2222222222", LocalDateTime.now(), LocalDateTime.now()));

        Page<Patient> page = new PageImpl<>(patients);
        when(patientRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<PatientProfileResponse> result = service.getPatients(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("p1", result.getContent().get(0).patientId());
        assertEquals("p2", result.getContent().get(1).patientId());
    }
}