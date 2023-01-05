package com.example.emailsender.services;

import com.example.emailsender.entities.CronEntity;
import com.example.emailsender.exceptions.RequestException;
import com.example.emailsender.repositories.CronRepository;
import com.example.emailsender.repositories.LogRepository;
import com.example.emailsender.repositories.UserRepository;
import com.example.emailsender.thread.RunSendMail;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleService {

    private final TaskScheduler taskScheduler;
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final LogRepository logRepository;
    private final CronRepository cronRepository;

    private ScheduledFuture<?> scheduler;

    public void startSchedule(int cronId) {
        scheduler = taskScheduler.schedule(
                new Thread(new RunSendMail(javaMailSender, userRepository, logRepository)),
                createExpression(cronId)
        );
    }

    public void editCron(int cronId) {
        scheduler.cancel(true);
        startSchedule(cronId);
    }

    private CronTrigger createExpression(int cronId) {
        return new CronTrigger(checkExistCron(cronId));

    }

    private String checkExistCron(int cronId) {
        Optional<CronEntity> cronById = cronRepository.findById(cronId);
        if (cronById.isPresent()) {
            return cronById.get().getExpression();
        } else {
            throw new RequestException("Cron not found");
        }
    }

}
