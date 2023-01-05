package com.example.emailsender.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCronRequest {

    @NotBlank
    private String cron;
}
