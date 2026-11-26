package com.ew.medicalcentre.patient.service;

import com.ew.medicalcentre.patient.dto.PatientProfileResponse;
import com.ew.medicalcentre.patient.model.Patient;
import com.ew.medicalcentre.patient.repository.PatientRepository;
import com.ew.medicalcentre.patient.exception.PatientNotFoundException;
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
     * @param id the patient ID
     * @return patient profile response
     * @throws PatientNotFoundException if patient not found
     */
    public PatientProfileResponse getPatientById(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found: " + id));

        return mapToProfileResponse(patient);
    }

    /**
     * Lists patients with pagination.
     * @param pageable pagination info
     * @return page of patient profiles
     */
    public Page<PatientProfileResponse> getPatients(Pageable pageable) {
        return patientRepository.findAll(pageable).map(this::mapToProfileResponse);
    }

    private PatientProfileResponse mapToProfileResponse(Patient patient) {
        PatientProfileResponse response = new PatientProfileResponse(
                patient.patientId(),
                patient.email(),
                patient.firstName(),
                patient.lastName(),
                patient.dateOfBirth() != null ? patient.dateOfBirth().toString() : "",
                patient.phone() != null ? patient.phone() : "",
                patient.createdAt());
        return response;
    }
}