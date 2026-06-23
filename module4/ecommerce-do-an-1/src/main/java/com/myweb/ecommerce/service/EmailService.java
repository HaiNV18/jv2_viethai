package com.myweb.ecommerce.service;

import com.myweb.ecommerce.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

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

    public void sendEmailOrderSuccess(EmailDto emailDto) throws MessagingException {
        Context context = new Context();
        context.setVariable("customerName", emailDto.getCustomerName());
        String html = templateEngine.process("email/order-success", context);

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(
                        message,
                        true,
                        "UTF-8"
                );

        helper.setTo(emailDto.getEmailTo());
        helper.setSubject(emailDto.getSubject());

        // true = HTML
        helper.setText(html, true);

        mailSender.send(message);
    }
}
