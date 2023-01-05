package com.example.emailsender.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EditExpressionForSchedulerRequest {

    @NotNull
    private int cronId;
}
