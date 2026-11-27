package com.ew.medicalcentre.patient.service;

import com.ew.medicalcentre.patient.dto.PatientProfileResponse;
import com.ew.medicalcentre.patient.dto.PatientRegistrationRequest;
import com.ew.medicalcentre.patient.event.PatientEventPublisher;
import com.ew.medicalcentre.patient.event.PatientRegisteredEvent;
import com.ew.medicalcentre.patient.exception.PatientAlreadyExistsException;
import com.ew.medicalcentre.patient.model.Patient;
import com.ew.medicalcentre.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Service responsible for patient registration.
 * Validates uniqueness, persists the patient, and publishes the registration event.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PatientRegistrationService {

    private final PatientRepository patientRepository;
    private final PatientEventPublisher patientEventPublisher;

    /**
     * Registers a new patient with the given registration request.
     *
     * @param request the registration request containing patient details
     * @return the patient profile response
     * @throws PatientAlreadyExistsException if the email is already registered
     */
    public PatientProfileResponse registerPatient(PatientRegistrationRequest request) {
        if (patientRepository.existsByEmail(request.email())) {
            throw new PatientAlreadyExistsException(request.email());
        }

        // Build Patient record (patientId + timestamps auto-generated)
        Patient patient = new Patient(
                null,
                request.email(),
                request.firstName(),
                request.lastName(),
                LocalDate.parse(request.dateOfBirth()),
                request.phone(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Patient savedPatient = patientRepository.save(patient);

        PatientProfileResponse response = new PatientProfileResponse(
                savedPatient.patientId(),
                savedPatient.email(),
                savedPatient.firstName(),
                savedPatient.lastName(),
                savedPatient.dateOfBirth() != null ? savedPatient.dateOfBirth().toString() : "",
                savedPatient.phone() != null ? savedPatient.phone() : "",
                savedPatient.createdAt()
        );

        patientEventPublisher.publishPatientRegistered(new PatientRegisteredEvent(
                savedPatient.patientId(),
                savedPatient.email(),
                savedPatient.firstName(),
                savedPatient.lastName(),
                savedPatient.dateOfBirth() != null ? savedPatient.dateOfBirth().toString() : "",
                savedPatient.phone() != null ? savedPatient.phone() : ""
        ));

        log.info("Registered patient with id {}", savedPatient.patientId());
        return response;
    }
}