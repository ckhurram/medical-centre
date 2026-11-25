package com.ew.medicalcentre.acceptance;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Sanity check that acceptance-test module is configured correctly.
 * Actual E2E tests require services running via docker-compose.
 */
public class AcceptanceTestConfigTest {

    @Test
    void moduleIsConfigured() {
        assertThat(AcceptanceTestConfigTest.class.getPackageName())
            .isEqualTo("com.ew.medicalcentre.acceptance");
    }
}