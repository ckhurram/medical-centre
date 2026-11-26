package com.ew.medicalcentre.patient.dto;

import java.util.List;

/**
 * RFC 7807 Problem Details for HTTP APIs.
 * Aligned with OpenAPI ErrorResponse schema.
 */
public class ErrorResponse {

    private int status;
    private String title;
    private String detail;
    private String instance;
    private List<FieldError> errors;

    public ErrorResponse() {}

    public ErrorResponse(int status, String title, String detail, String instance) {
        this.status = status;
        this.title = title;
        this.detail = detail;
        this.instance = instance;
    }

    public static class FieldError {
        private String field;
        private String message;

        public FieldError() {}
        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }
        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    // Getters and Setters
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    public String getInstance() { return instance; }
    public void setInstance(String instance) { this.instance = instance; }

    public List<FieldError> getErrors() { return errors; }
    public void setErrors(List<FieldError> errors) { this.errors = errors; }
}