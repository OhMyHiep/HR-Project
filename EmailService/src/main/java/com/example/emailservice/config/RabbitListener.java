package com.example.emailservice.config;

import com.example.emailservice.service.EmailService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.SerializationUtils;
import java.io.*;
import java.util.Arrays;

@Component
public class RabbitListener implements MessageListener {

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService){this.emailService = emailService;}

    @Override
    public void onMessage(Message message) {
        String m = new String(message.getBody());
        String[] str = m.split("\\b\\+++\\b");
        String subject= str[1].contains("rejection")? "Application rejection" :"Registration token";

        try {
            emailService.sendEmail(str[0],subject,str[1]);
        }catch (Exception e){
            e.printStackTrace();
        }
//        str[0] = email to send message to
//        str[1] = message content
//        System.out.println("New message received from " + message.getMessageProperties().getConsumerQueue() + ": " + new String(message.getBody()));
    }
}
