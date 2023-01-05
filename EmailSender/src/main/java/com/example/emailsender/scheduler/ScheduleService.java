package com.example.emailsender.scheduler;

import com.example.emailsender.exceptions.RequestException;
import com.example.emailsender.repositories.LogRepository;
import com.example.emailsender.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleService {

    private final TaskScheduler taskScheduler;
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final LogRepository logRepository;

    private ScheduledFuture<?> scheduler;

    public void startSchedule(String expression, String subject) {
        scheduler = taskScheduler.schedule(
                new Thread(new RunSendMail(javaMailSender, userRepository, logRepository, subject)),
                createExpression(expression)
        );
    }

    public void editCron(String expression, String subject) {
        scheduler.cancel(true);
        startSchedule(expression, subject);
    }

    private CronTrigger createExpression(String expression) {
        if (CronExpression.isValidExpression(expression)) {
            return new CronTrigger(expression);
        } else {
            throw new RequestException("Invalid expression");
        }
    }

}
