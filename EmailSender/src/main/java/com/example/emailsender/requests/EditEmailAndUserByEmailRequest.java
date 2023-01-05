package com.example.emailsender.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EditEmailAndUserByEmailRequest {

    @NotBlank
    @Email(message = "Email is not valid")
    private String email;
}
