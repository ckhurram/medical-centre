package com.ew.medicalcentre.patient.controller;

import com.ew.medicalcentre.patient.dto.PatientProfileResponse;
import com.ew.medicalcentre.patient.dto.PatientRegistrationRequest;
import com.ew.medicalcentre.patient.service.PatientQueryService;
import com.ew.medicalcentre.patient.service.PatientRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientRegistrationService patientRegistrationService;
    private final PatientQueryService patientQueryService;

    public PatientController(PatientRegistrationService patientRegistrationService,
                             PatientQueryService patientQueryService) {
        this.patientRegistrationService = patientRegistrationService;
        this.patientQueryService = patientQueryService;
    }

    /**
     * Public endpoint to register a new patient.
     * No authentication required - any patient can register.
     *
     * @param request the registration request
     * @return 201 Created with patient profile response
     */
    @PostMapping
    public ResponseEntity<PatientProfileResponse> registerPatient(
            @Valid @RequestBody PatientRegistrationRequest request) {
        PatientProfileResponse response = patientRegistrationService.registerPatient(request);
        return ResponseEntity.created(URI.create("/patients/" + response.getPatientId()))
                .body(response);
    }

    /**
     * Gets a patient profile by ID.
     *
     * @param id the patient ID
     * @return patient profile response
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientProfileResponse> getPatientProfile(@PathVariable String id) {
        PatientProfileResponse profile = patientQueryService.getPatientById(id);
        return ResponseEntity.ok(profile);
    }
}