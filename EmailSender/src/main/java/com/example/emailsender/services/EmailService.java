package com.example.emailsender.services;

import com.example.emailsender.entities.LogEntity;
import com.example.emailsender.entities.UserEntity;
import com.example.emailsender.enums.MessageType;
import com.example.emailsender.exceptions.RequestException;
import com.example.emailsender.repositories.LogRepository;
import com.example.emailsender.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String SENDER_EMAIL = "senderEmail@gmail.com";

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final LogRepository logRepository;

    public void sendEmail(long userId, String subject, String message) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(SENDER_EMAIL);
            mailMessage.setSubject(subject);
            mailMessage.setTo(user.get().getEmail());
            mailMessage.setText(message);
            javaMailSender.send(mailMessage);
            createLog(user.get());
        } else {
            throw new RequestException("User not found");
        }
    }

    private void createLog(UserEntity user) {
        LogEntity logEntity = new LogEntity();
        logEntity.setType(MessageType.REST);
        logEntity.setUser(user);
        logRepository.save(logEntity);
    }
}
