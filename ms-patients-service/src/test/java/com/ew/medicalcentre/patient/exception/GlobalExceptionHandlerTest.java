package com.ew.medicalcentre.patient.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ew.medicalcentre.patient.dto.ErrorResponse;

class GlobalExceptionHandlerTest {

    @Mock
    private HttpServletRequest request;

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getRequestURI()).thenReturn("/api/v1/patients");
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handlePatientAlreadyExists_returnsBadRequest() {
        PatientAlreadyExistsException ex = new PatientAlreadyExistsException("john.doe@example.com");

        ResponseEntity<ErrorResponse> result = handler.handlePatientAlreadyExists(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(400, result.getBody().status());
        assertEquals("Duplicate Email", result.getBody().title());
        assertTrue(result.getBody().detail().contains("john.doe@example.com"));
    }

    @Test
    void handlePatientNotFound_returnsNotFound() {
        PatientNotFoundException ex = new PatientNotFoundException("patient-123");

        ResponseEntity<ErrorResponse> result = handler.handlePatientNotFound(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(404, result.getBody().status());
    }

    @Test
    void handleValidationErrors_returnsBadRequest() throws Exception {
        // Build a real MethodArgumentNotValidException with a field error
        org.springframework.validation.BeanPropertyBindingResult bindingResult =
                new org.springframework.validation.BeanPropertyBindingResult(new TestBean(), "testBean");
        bindingResult.rejectValue("email", "invalid", "must be valid");

        java.lang.reflect.Method method = TestController.class.getDeclaredMethod("handle", TestBean.class);
        org.springframework.core.MethodParameter methodParameter =
                new org.springframework.core.MethodParameter(method, 0);
        org.springframework.web.bind.MethodArgumentNotValidException ex =
                new org.springframework.web.bind.MethodArgumentNotValidException(methodParameter, bindingResult);

        ResponseEntity<ErrorResponse> result = handler.handleValidationErrors(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(400, result.getBody().status());
        assertEquals("Validation Error", result.getBody().title());
        assertNotNull(result.getBody().errors());
        assertEquals(1, result.getBody().errors().size());
    }

    static class TestBean {
        private String email;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    static class TestController {
        public void handle(TestBean bean) {}
    }

    @Test
    void handleGenericException_returnsInternalServerError() {
        Exception ex = new Exception("Something went wrong");

        ResponseEntity<ErrorResponse> result = handler.handleGenericException(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals(500, result.getBody().status());
        assertEquals("Internal Server Error", result.getBody().title());
    }
}