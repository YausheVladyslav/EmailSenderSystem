package com.example.emailsender.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EmailAutoRequest {

    @NotNull
    private long userId;
    @NotBlank
    @Size(min = 2, max = 64)
    private String subject;
}
