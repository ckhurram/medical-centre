package com.ew.medicalcentre.patient.service;

import com.ew.medicalcentre.patient.dto.*;
import com.ew.medicalcentre.patient.model.Patient;
import com.ew.medicalcentre.patient.model.Email;
import com.ew.medicalcentre.patient.model.PhoneNumber;
import com.ew.medicalcentre.patient.repository.PatientRepository;
import com.ew.medicalcentre.patient.event.PatientEventPublisher;
import com.ew.medicalcentre.patient.event.PatientRegisteredEvent;
import com.ew.medicalcentre.patient.exception.PatientAlreadyExistsException;
import com.ew.medicalcentre.patient.exception.PatientNotFoundException;
import org.springframework.stereotype.Service;

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
     * @param request the registration request containing patient details
     * @return the patient profile response
     * @throws PatientAlreadyExistsException if the email is already registered
     */
    public PatientProfileResponse registerPatient(PatientRegistrationRequest request) {
        // Check if email already exists
        if (patientRepository.existsByEmail(request.getEmail())) {
            throw new PatientAlreadyExistsException("Email already registered: " + request.getEmail());
        }

        // Map to Patient entity
        Patient patient = new Patient();
        patient.setEmail(request.getEmail());
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());

        // Parse date of birth (assuming ISO-8601 format)
        try {
            java.time.LocalDate dob = java.time.LocalDate.parse(request.getDateOfBirth());
            patient.setDateOfBirth(dob);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date of birth format: " + request.getDateOfBirth());
        }

        // Parse phone using PhoneNumber value object
        try {
            PhoneNumber phone = new PhoneNumber(request.getPhone());
            patient.setPhone(phone.getValue());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid phone number format: " + request.getPhone());
        }

        // Set timestamps
        patient.setCreatedAt(java.time.LocalDateTime.now());
        patient.setUpdatedAt(java.time.LocalDateTime.now());

        // Save to MongoDB
        Patient savedPatient = patientRepository.save(patient);

        // Build profile response
        PatientProfileResponse response = new PatientProfileResponse(
                savedPatient.getPatientId(),
                savedPatient.getEmail(),
                savedPatient.getFirstName(),
                savedPatient.getLastName(),
                savedPatient.getDateOfBirth() != null ? savedPatient.getDateOfBirth().toString() : "",
                savedPatient.getPhone() != null ? savedPatient.getPhone() : "",
                savedPatient.getCreatedAt());

        // Publish event
        PatientRegisteredEvent event = new PatientRegisteredEvent(
                savedPatient.getPatientId(),
                savedPatient.getEmail(),
                savedPatient.getFirstName(),
                savedPatient.getLastName(),
                savedPatient.getDateOfBirth() != null ? savedPatient.getDateOfBirth().toString() : "",
                savedPatient.getPhone() != null ? savedPatient.getPhone() : "");

        patientEventPublisher.publishPatientRegistered(event);

        return response;
    }
}