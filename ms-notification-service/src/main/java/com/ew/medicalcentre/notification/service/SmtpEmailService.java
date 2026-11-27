package com.ew.medicalcentre.notification.service;

import com.ew.medicalcentre.notification.event.PatientRegisteredEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Production email service that sends welcome emails via SMTP.
 * Active when the "smtp" profile is enabled.
 */
@Slf4j
@Service
@Profile("smtp")
@RequiredArgsConstructor
public class SmtpEmailService implements EmailService {

    private final JavaMailSender javaMailSender;

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