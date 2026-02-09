package com.natanight.petproject.dto.user;

import com.natanight.petproject.dto.annotation.EmailValidation;
import com.natanight.petproject.dto.annotation.PasswordValidation;
import com.natanight.petproject.dto.annotation.UsernameValidation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateUserRequest {
    @NotNull
    @NotEmpty(message = "Username should not be empty")
    @UsernameValidation
    private String username;

    @NotNull
    @NotEmpty(message = "Email should not be empty")
    @EmailValidation
    private String email;

    @NotNull
    @PasswordValidation
    private String password;

    public CreateUserRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
