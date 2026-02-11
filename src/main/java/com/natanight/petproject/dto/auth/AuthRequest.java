package com.natanight.petproject.dto.auth;

import com.natanight.petproject.dto.annotation.PasswordValidation;
import com.natanight.petproject.dto.annotation.UsernameValidation;
import jakarta.validation.constraints.NotNull;

public class AuthRequest {
    @NotNull
    @UsernameValidation
    private String username;
    @NotNull
    @PasswordValidation
    private String password;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
