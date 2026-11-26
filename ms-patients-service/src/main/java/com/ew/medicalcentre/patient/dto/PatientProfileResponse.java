package com.ew.medicalcentre.patient.dto;

import java.time.LocalDateTime;

/**
 * DTO for patient profile response.
 * Aligned with OpenAPI PatientProfile schema.
 */
public class PatientProfileResponse {

    private String patientId;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phone;
    private LocalDateTime createdAt;

    // Default constructor
    public PatientProfileResponse() {}

    public PatientProfileResponse(String patientId, String email, String firstName,
                                  String lastName, String dateOfBirth, String phone,
                                  LocalDateTime createdAt) {
        this.patientId = patientId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}