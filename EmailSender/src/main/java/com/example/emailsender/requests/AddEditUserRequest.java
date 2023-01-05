package com.example.emailsender.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AddEditUserRequest {

    @NotBlank
    @Size(min = 2, max = 64, message = "Username should be 2 to 64 symbols")
    private String username;
    @NotBlank
    @Email(message = "Email is not valid")
    private String email;
}
