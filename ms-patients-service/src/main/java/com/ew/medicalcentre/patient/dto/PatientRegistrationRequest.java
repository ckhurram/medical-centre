package com.ew.medicalcentre.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO for patient registration request.
 * Aligned with OpenAPI PatientRegistrationRequest schema.
 *
 * Implemented as a Java record with Lombok — immutable, with auto-generated
 * accessors, equals, hashCode, and toString. Validation constraints remain
 * on the record components for Spring validation.
 */
public record PatientRegistrationRequest(
        @Email(message = "Email must be valid")
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "First name is required")
        @Size(min = 1, max = 50)
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 1, max = 50)
        String lastName,

        @Past(message = "Date of birth must be in the past")
        @NotNull(message = "Date of birth is required")
        String dateOfBirth,

        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone must be in E.164 format")
        String phone
) {
}
