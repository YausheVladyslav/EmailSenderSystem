package com.example.emailsender.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogStatisticResponse implements Comparator<LogStatisticResponse> {

    private String username;
    private String email;
    private CountForStatisticResponse count;
    private String first;
    private String last;

    @Override
    public int compare(LogStatisticResponse o1, LogStatisticResponse o2) {
        return Integer.compare(o2.getCount().getRest() + o2.getCount().getCron(),
                o1.getCount().getRest() + o1.getCount().getCron());
    }
}
