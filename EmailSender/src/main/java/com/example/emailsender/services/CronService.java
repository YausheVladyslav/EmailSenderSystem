package com.example.emailsender.services;

import com.example.emailsender.entities.CronEntity;
import com.example.emailsender.exceptions.RequestException;
import com.example.emailsender.repositories.CronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CronService {

    private final CronRepository cronRepository;

    private static final int PAGE_SIZE = 5;

    private boolean validCron(String expression) {
        return CronExpression.isValidExpression(expression);
    }

    public void createCron(String expression) {
        if (validCron(expression)) {
            CronEntity cron = new CronEntity();
            cron.setExpression(expression);
            cronRepository.save(cron);
        } else {
            throw new RequestException("Expression is not valid");
        }
    }

    public void editCron(int cronId, String newExpression) {
        Optional<CronEntity> cronEntity = cronRepository.findById(cronId);
        if (cronEntity.isPresent()) {
            if (validCron(newExpression)) {
                cronEntity.get().setExpression(newExpression);
                cronRepository.save(cronEntity.get());
            } else {
                throw new RequestException("Expression is not valid");
            }
        } else {
            throw new RequestException("Cron by this id: " + cronId + " is no found");
        }
    }

    public void deleteCron(int cronId) {
        Optional<CronEntity> cronEntity = cronRepository.findById(cronId);
        cronEntity.ifPresent(cronRepository::delete);
    }

    public Page<CronEntity> allCrons(int pageNumber) {
        Pageable paging = PageRequest.of(pageNumber, PAGE_SIZE);
        return cronRepository.findAll(paging);
    }
}
