package com.example.emailsender.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountForStatisticResponse {

    private int rest;
    private int cron;

    public CountForStatisticResponse(int rest, int cron) {
        this.rest = rest;
        this.cron = cron;
    }
}
