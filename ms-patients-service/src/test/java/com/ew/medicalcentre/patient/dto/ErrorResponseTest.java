package com.ew.medicalcentre.patient.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ErrorResponseTest {

    @Test
    void testBadRequest() {
        ErrorResponse error = ErrorResponse.badRequest("/patients", "Invalid input");
        assertEquals(400, error.status());
        assertEquals("Bad Request", error.title());
        assertEquals("Invalid input", error.detail());
        assertEquals("/patients", error.instance());
        assertTrue(true);
    }

    @Test
    void testNotFound() {
        ErrorResponse error = ErrorResponse.notFound("/patients/123", "Patient not found");
        assertEquals(404, error.status());
        assertEquals("Not Found", error.title());
        assertEquals("Patient not found", error.detail());
        assertEquals("/patients/123", error.instance());
    }

    @Test
    void testFieldError() {
        ErrorResponse.FieldError error = new ErrorResponse.FieldError("email", "Invalid email");
        assertEquals("email", error.field());
        assertEquals("Invalid email", error.message());
    }

    @Test
    void testValidationError() {
        ErrorResponse.FieldError fe = new ErrorResponse.FieldError("email", "must be valid");
        ErrorResponse error = ErrorResponse.validationError("/patients", List.of(fe));
        assertEquals(400, error.status());
        assertEquals("Validation Error", error.title());
        assertEquals(1, error.errors().size());
    }

    @Test
    void testInternalError() {
        ErrorResponse error = ErrorResponse.internalError("/patients", "boom");
        assertEquals(500, error.status());
        assertEquals("Internal Server Error", error.title());
        assertEquals("boom", error.detail());
    }

    @Test
    void testRule() {
    }
}