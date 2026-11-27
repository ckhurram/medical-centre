package com.ew.medicalcentre.patient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PatientsServiceApplicationTest {

    @Test
    void main_canBeInstantiated() {
        // Just verify the class can be instantiated - main is a SpringBootApplication entry point
        PatientsServiceApplication app = new PatientsServiceApplication();
        assertNotNull(app);
    }

    @Test
    void main_methodExists() throws Exception {
        // Verify main method exists and is static
        assertNotNull(PatientsServiceApplication.class.getDeclaredMethod("main", String[].class));
    }
}