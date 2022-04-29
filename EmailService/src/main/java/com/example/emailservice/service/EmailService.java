package com.example.emailservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Component
public class EmailService {
    @Resource
    private JavaMailSender emailSender;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setFrom("lehoangdung29111998@gmail.com");
        email.setTo(toEmail);
        email.setText(body);
        emailSender.send(email);
        System.out.println("Email send success!");
    }

}
