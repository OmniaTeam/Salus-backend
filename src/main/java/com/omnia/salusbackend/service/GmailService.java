package com.omnia.salusbackend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GmailService {

    public final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public void sendSimpleEmail(String toAddress, String fio, LocalDateTime date ,String lector, String subject, String description, String place, String link) {

        MimeMessage message = emailSender.createMimeMessage();

        String htmlContent = templateEngine.process("temp.html", createContext(fio,date, lector, subject, description, place, link));

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("Новая запись!");
            helper.setTo(toAddress);
            helper.setText(htmlContent, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        emailSender.send(message);
    }


    private Context createContext(String fio, LocalDateTime date, String lector, String subject, String description, String place, String link) {
        Context context = new Context();
        context.setVariable("recipient", fio);
        context.setVariable("date", date);
        context.setVariable("lector", lector);
        context.setVariable("subject", subject);
        context.setVariable("description", description);
        context.setVariable("place", place);
        context.setVariable("link", link);
        return context;
    }

}
