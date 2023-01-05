package com.example.emailsender.services;

import com.example.emailsender.entities.LogEntity;
import com.example.emailsender.entities.UserEntity;
import com.example.emailsender.enums.MessageType;
import com.example.emailsender.repositories.LogRepository;
import com.example.emailsender.repositories.UserRepository;
import com.example.emailsender.responses.CountForStatisticResponse;
import com.example.emailsender.responses.LogStatisticResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final UserRepository userRepository;

    private static final int PAGE_SIZE = 5;

    public List<LogStatisticResponse> getLogStatistic(int pageNumber) {
        List<UserEntity> userList = userRepository.findAll();
        List<LogStatisticResponse> statistic = new ArrayList<>();
        for (UserEntity user : userList) {
            List<LogEntity> usersLog = logRepository.findAllByUser(user);
            List<LogEntity> rest = logRepository.findAllByTypeAndUser(MessageType.REST, user);
            List<LogEntity> cron = logRepository.findAllByTypeAndUser(MessageType.CRON, user);
            fillListWithStatistic(statistic, user, rest.size(), cron.size(), usersLog);
        }
        return sortStatisticPages(statistic, pageNumber);
    }

    private void fillListWithStatistic(List<LogStatisticResponse> statisticList,
                                 UserEntity user,
                                 int restCount,
                                 int cronCount,
                                 List<LogEntity> logDate) {
        if (logDate.size() == 0) {
            statisticList.add(new LogStatisticResponse(
                    user.getUsername(),
                    user.getEmail(),
                    new CountForStatisticResponse(restCount, cronCount),
                    null,
                    null));
        } else {
            statisticList.add(new LogStatisticResponse(
                    user.getUsername(),
                    user.getEmail(),
                    new CountForStatisticResponse(restCount, cronCount),
                    logDate.get(0).getCreatedOn().toString(),
                    logDate.get(logDate.size() - 1).getCreatedOn().toString()));
        }
    }

    private List<LogStatisticResponse> sortStatisticPages(
            List<LogStatisticResponse> statistic, int pageNumber) {
        Collections.sort(statistic, new LogStatisticResponse());
        PagedListHolder<LogStatisticResponse> page = new PagedListHolder<>(statistic);
        page.setPageSize(PAGE_SIZE);
        page.setPage(pageNumber);
        return page.getPageList();
    }
}
