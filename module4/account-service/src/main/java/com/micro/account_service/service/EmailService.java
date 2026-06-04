package com.micro.account_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(
            String emailTo,
            String subject,
            String body
    ) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailTo);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
