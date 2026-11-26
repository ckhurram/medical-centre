package com.ew.medicalcentre.notification.service;

import com.ew.medicalcentre.notification.event.PatientRegisteredEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Production email service that sends welcome emails via SMTP.
 * Active when the "smtp" profile is enabled.
 */
@Service
@Profile("smtp")
public class SmtpEmailService implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);

    private final JavaMailSender javaMailSender;

    public SmtpEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendWelcomeEmail(PatientRegisteredEvent event) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            helper.setFrom("noreply@medicalcentre.com");
            helper.setTo(event.getEmail());
            helper.setSubject("Welcome to Medical Centre - Registration Complete");
            helper.setText(
                    "Dear " + event.getFirstName() + " " + event.getLastName() + ",\n\n"
                    + "Welcome to the Medical Centre! Your patient registration is complete.\n"
                    + "Patient ID: " + event.getPatientId() + "\n\n"
                    + "You can now log in to your account and start booking appointments.\n\n"
                    + "Best regards,\n"
                    + "Medical Centre Administration");

            javaMailSender.send(message);
            log.info("Welcome email sent to: {}", event.getEmail());
        } catch (MessagingException e) {
            log.error("Failed to send welcome email to: {}", event.getEmail(), e);
            throw new RuntimeException("Failed to send welcome email", e);
        }
    }
}