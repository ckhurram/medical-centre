package com.ew.medicalcentre.patient.service;

import com.ew.medicalcentre.patient.dto.PatientProfileResponse;
import com.ew.medicalcentre.patient.model.Patient;
import com.ew.medicalcentre.patient.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientQueryService {

    private final PatientRepository patientRepository;

    public PatientQueryService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Gets a patient profile by ID.
     *
     * @param id the patient ID
     * @return patient profile response
     */
    public PatientProfileResponse getPatientById(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(
                        () -> new java.lang.RuntimeException("Patient not found: " + id));
        return new PatientProfileResponse(
                patient.patientId(),
                patient.email(),
                patient.firstName(),
                patient.lastName(),
                patient.dateOfBirth() != null ? patient.dateOfBirth().toString() : "",
                patient.phone() != null ? patient.phone() : "",
                patient.createdAt()
        );
    }

    /**
     * Lists patients with pagination.
     *
     * @param pageable pagination info
     * @return page of patient profiles
     */
    public Page<PatientProfileResponse> getPatients(Pageable pageable) {
        return patientRepository.findAll(pageable).map(patient -> new PatientProfileResponse(
                patient.patientId(),
                patient.email(),
                patient.firstName(),
                patient.lastName(),
                patient.dateOfBirth() != null ? patient.dateOfBirth().toString() : "",
                patient.phone() != null ? patient.phone() : "",
                patient.createdAt()));
    }
}