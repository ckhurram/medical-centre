package com.ew.medicalcentre.patient.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Patient aggregate root representing a registered patient in the Medical Centre system.
 * Stored in the "patients" MongoDB collection.
 *
 * <p>Implemented as a Java record — immutable, with auto-generated accessors,
 * equals, hashCode, and toString.</p>
 */
@Document(collection = "patients")
public record Patient(
        @Id
        @Field("patientId")
        String patientId,

        @Email(message = "Email must be valid")
        @NotBlank(message = "Email is required")
        @Indexed(unique = true)
        @Field("email")
        String email,

        @NotBlank(message = "First name is required")
        @Size(min = 1, max = 50)
        @Field("firstName")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 1, max = 50)
        @Field("lastName")
        String lastName,

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        @Field("dateOfBirth")
        LocalDate dateOfBirth,

        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone must be in E.164 format")
        @Field("phone")
        String phone,

        @Field("createdAt")
        LocalDateTime createdAt,

        @Field("updatedAt")
        LocalDateTime updatedAt
) {

    /**
     * Compact constructor — generates a patientId when none is supplied
     * (e.g. during registration) and stamps creation/update times.
     */
    public Patient {
        if (patientId == null || patientId.isBlank()) {
            patientId = UUID.randomUUID().toString();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = createdAt;
        }
    }
}