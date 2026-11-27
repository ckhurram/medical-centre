package com.ew.medicalcentre.patient.service;

import com.ew.medicalcentre.patient.dto.PatientProfileResponse;
import com.ew.medicalcentre.patient.exception.PatientNotFoundException;
import com.ew.medicalcentre.patient.model.Patient;
import com.ew.medicalcentre.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Read-side service for patient queries.
 */
@Service
@RequiredArgsConstructor
public class PatientQueryService {

    private final PatientRepository patientRepository;

    public PatientProfileResponse getPatientById(String id) {
        return patientRepository.findById(id)
                .map(PatientQueryService::toResponse)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    public Page<PatientProfileResponse> getPatients(Pageable pageable) {
        return patientRepository.findAll(pageable).map(PatientQueryService::toResponse);
    }

    private static PatientProfileResponse toResponse(Patient patient) {
        return new PatientProfileResponse(
                patient.patientId(),
                patient.email(),
                patient.firstName(),
                patient.lastName(),
                patient.dateOfBirth() != null ? patient.dateOfBirth().toString() : "",
                patient.phone() != null ? patient.phone() : "",
                patient.createdAt());
    }
}
