package com.example.bom.gabom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void send(SimpleMailMessage email) {
        javaMailSender.send(email);
    }
}