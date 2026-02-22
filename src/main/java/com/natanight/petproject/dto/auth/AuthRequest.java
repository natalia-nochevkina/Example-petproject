package com.natanight.petproject.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.natanight.petproject.dto.annotation.PasswordValidation;
import com.natanight.petproject.dto.annotation.UsernameValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = false)
public class AuthRequest {
    @NotBlank
    @UsernameValidation
    private String username;

    @NotBlank
    @PasswordValidation
    private String password;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
