package com.example.emailsender.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
@NoArgsConstructor
public class LogStatisticResponse implements Comparator<LogStatisticResponse> {


    private String username;
    private String email;
    CountForStatisticResponse count;
    private String first;
    private String last;

    public LogStatisticResponse(String username,
                                String email,
                                CountForStatisticResponse count,
                                String first,
                                String last) {
        this.username = username;
        this.email = email;
        this.count = count;
        this.first = first;
        this.last = last;
    }

    @Override
    public int compare(LogStatisticResponse o1, LogStatisticResponse o2) {
        return Integer.compare(o1.getCount().getRest() + o1.getCount().getCron(),
                o2.getCount().getRest() + o2.getCount().getCron());
    }
}
