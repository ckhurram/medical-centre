package com.ew.medicalcentre.notification.service;

import com.ew.medicalcentre.notification.event.PatientRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Development email service that logs welcome emails instead of sending them.
 * Default service (active unless the "smtp" profile is enabled).
 */
@Service
@Profile("!smtp")
public class LoggingEmailService implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(LoggingEmailService.class);

    @Override
    public void sendWelcomeEmail(PatientRegisteredEvent event) {
        log.info("=======================================================");
        log.info("[WELCOME EMAIL - DEV MODE]");
        log.info("To: {} {} <{}>", event.getFirstName(), event.getLastName(), event.getEmail());
        log.info("Subject: Welcome to Medical Centre");
        log.info("Body: Dear {} {}, your registration is complete.",
                event.getFirstName(), event.getLastName());
        log.info("Patient ID: {}", event.getPatientId());
        log.info("=======================================================");
    }
}