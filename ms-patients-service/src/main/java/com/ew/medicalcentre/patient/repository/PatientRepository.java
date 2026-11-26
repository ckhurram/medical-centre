package com.ew.medicalcentre.patient.repository;

import com.ew.medicalcentre.patient.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    Patient findByEmail(String email);
    boolean existsByEmail(String email);
}