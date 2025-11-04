package com.mertalptekin.springbootrestapp.infra.service;

import org.springframework.stereotype.Component;

@Component
public class TurkcellEmailSender implements IEmailSender {
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println(to + subject + body);
    }
}
