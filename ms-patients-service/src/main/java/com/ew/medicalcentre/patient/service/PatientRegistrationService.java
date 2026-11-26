package com.ew.medicalcentre.patient.service;

import com.ew.medicalcentre.patient.dto.PatientProfileResponse;
import com.ew.medicalcentre.patient.dto.PatientRegistrationRequest;
import com.ew.medicalcentre.patient.event.PatientEventPublisher;
import com.ew.medicalcentre.patient.event.PatientRegisteredEvent;
import com.ew.medicalcentre.patient.exception.PatientAlreadyExistsException;
import com.ew.medicalcentre.patient.model.Patient;
import com.ew.medicalcentre.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Service responsible for patient registration.
 * Validates uniqueness, persists the patient, and publishes the registration event.
 */
@Service
public class PatientRegistrationService {

    private final PatientRepository patientRepository;
    private final PatientEventPublisher patientEventPublisher;

    public PatientRegistrationService(PatientRepository patientRepository,
                                       PatientEventPublisher patientEventPublisher) {
        this.patientRepository = patientRepository;
        this.patientEventPublisher = patientEventPublisher;
    }

    /**
     * Registers a new patient with the given registration request.
     *
     * @param request the registration request containing patient details
     * @return the patient profile response
     * @throws PatientAlreadyExistsException if the email is already registered
     */
    public PatientProfileResponse registerPatient(PatientRegistrationRequest request) {
        // Check if email already exists
        if (patientRepository.existsByEmail(request.getEmail())) {
            throw new PatientAlreadyExistsException("Email already registered: " + request.getEmail());
        }

        // Build Patient record from request data (patientId + timestamps auto-generated)
        Patient patient = new Patient(
                null,
                request.getEmail(),
                request.getFirstName(),
                request.getLastName(),
                LocalDate.parse(request.getDateOfBirth()),
                request.getPhone(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        // Save to MongoDB
        Patient savedPatient = patientRepository.save(patient);

        // Build profile response (record accessors)
        PatientProfileResponse response = new PatientProfileResponse(
                savedPatient.patientId(),
                savedPatient.email(),
                savedPatient.firstName(),
                savedPatient.lastName(),
                savedPatient.dateOfBirth() != null ? savedPatient.dateOfBirth().toString() : "",
                savedPatient.phone() != null ? savedPatient.phone() : "",
                savedPatient.createdAt());

        // Publish event
        PatientRegisteredEvent event = new PatientRegisteredEvent(
                savedPatient.patientId(),
                savedPatient.email(),
                savedPatient.firstName(),
                savedPatient.lastName(),
                savedPatient.dateOfBirth() != null ? savedPatient.dateOfBirth().toString() : "",
                savedPatient.phone() != null ? savedPatient.phone() : "");

        patientEventPublisher.publishPatientRegistered(event);

        return response;
    }
}