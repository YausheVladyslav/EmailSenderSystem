package com.example.emailsender.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EditUsernameRequest {

    @NotBlank
    @Size(min = 2, max = 64, message = "Username should be 2 to 64 symbols")
    private String username;
}
