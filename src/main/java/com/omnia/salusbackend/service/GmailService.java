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

@Service
@RequiredArgsConstructor
public class GmailService {

    public final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public void sendSimpleEmail(String toAddress, String fio, String lector, Integer meetRange, String subject, String description, String place, String link) {

        String htmlContent = templateEngine.process("temp.html", createContext(fio, lector, meetRange, subject, description, place, link));

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(fio);
        simpleMailMessage.setText(htmlContent);
        emailSender.send(simpleMailMessage);
    }


    private Context createContext(String fio, String lector, Integer meetRange, String subject, String description, String place, String link) {
        Context context = new Context();
        context.setVariable("fio", fio);
        context.setVariable("lector", lector);
        context.setVariable("meetRange", meetRange);
        context.setVariable("subject", subject);
        context.setVariable("description", description);
        context.setVariable("place", place);
        context.setVariable("link", link);
        return context;
    }

//    @Bean
    public void test() {
        sendSimpleEmail("breev.vadim@yandex.ru", "fio", "lector", 23,"mwekflw", "desc", "place", "link");
    }
}
