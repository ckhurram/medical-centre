package com.ew.medicalcentre.notification.service;

import com.ew.medicalcentre.notification.event.PatientRegisteredEvent;

/**
 * Interface for sending patient registration welcome emails.
 */
public interface EmailService {

    void sendWelcomeEmail(PatientRegisteredEvent event);
}