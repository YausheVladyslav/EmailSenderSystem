package com.example.emailsender.scheduler;

import com.example.emailsender.entities.LogEntity;
import com.example.emailsender.entities.UserEntity;
import com.example.emailsender.enums.MessageType;
import com.example.emailsender.repositories.LogRepository;
import com.example.emailsender.repositories.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RunSendMail implements Runnable {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final LogRepository logRepository;
    private static final String SENDER_EMAIL = "senderEmail@gmail.com";
    private String subject;

    public RunSendMail(
            JavaMailSender javaMailSender,
            UserRepository userRepository,
            LogRepository logRepository,
            String subject) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
        this.logRepository = logRepository;
        this.subject = subject;
    }

    @Transactional
    @Override
    public void run() {
        List<UserEntity> userList = userRepository.findAll();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        for (UserEntity user : userList) {
            mailMessage.setFrom(SENDER_EMAIL);
            mailMessage.setSubject(subject);
            mailMessage.setTo(user.getEmail());
            mailMessage.setText("Username: " + user.getUsername() + "\n"
                    + "Date created: " + user.getCreatedOn());
            javaMailSender.send(mailMessage);
            createLog(user);
        }
    }

    private void createLog(UserEntity user) {
        LogEntity logEntity = new LogEntity();
        logEntity.setType(MessageType.CRON);
        logEntity.setUser(user);
        logRepository.save(logEntity);
    }
}
