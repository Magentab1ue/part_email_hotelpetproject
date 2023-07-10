package com.example.email.listener;

import com.example.common.EmailRequest;
import com.example.email.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailListener {
    private final EmailService emailService;

    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "activation-email")
    public void listenForActivationEmail(EmailRequest request) throws MessagingException {
        log.info("Kafka received:" + request.getTo());
        log.info(request.getContent());

        emailService.send(request.getTo(), request.getSubject(),request.getContent());
    }
}
