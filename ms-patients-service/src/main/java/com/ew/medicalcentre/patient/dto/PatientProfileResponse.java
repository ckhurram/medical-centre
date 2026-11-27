package com.ew.medicalcentre.patient.dto;

import java.time.LocalDateTime;

/**
 * DTO for patient profile response.
 * Aligned with OpenAPI PatientProfile schema.
 */
public record PatientProfileResponse(
        String patientId,
        String email,
        String firstName,
        String lastName,
        String dateOfBirth,
        String phone,
        LocalDateTime createdAt
) {
}
