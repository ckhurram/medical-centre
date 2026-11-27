package com.ew.medicalcentre.patient.dto;

import java.util.List;

/**
 * RFC 7807 Problem Details for HTTP APIs.
 * Aligned with OpenAPI ErrorResponse schema.
 * Implemented as a Java record with Lombok for immutable error responses.
 */
public record ErrorResponse(
        int status,
        String title,
        String detail,
        String instance,
        List<FieldError> errors
) {

    public record FieldError(
            String field,
            String message
    ) {}

    public static ErrorResponse validationError(String instance, List<FieldError> errors) {
        return new ErrorResponse(400, "Validation Error", "Invalid input data", instance, errors);
    }

    public static ErrorResponse badRequest(String instance, String detail) {
        return new ErrorResponse(400, "Bad Request", detail, instance, List.of());
    }

    public static ErrorResponse notFound(String instance, String detail) {
        return new ErrorResponse(404, "Not Found", detail, instance, List.of());
    }

    public static ErrorResponse internalError(String instance, String detail) {
        return new ErrorResponse(500, "Internal Server Error", detail, instance, List.of());
    }

    public static ErrorResponse badRequestWithDetail(int status, String title, String detail, String instance) {
        return new ErrorResponse(status, title, detail, instance, List.of());
    }
}
