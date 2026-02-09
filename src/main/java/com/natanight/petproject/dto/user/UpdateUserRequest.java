package com.natanight.petproject.dto.user;

import com.natanight.petproject.dto.annotation.EmailValidation;
import com.natanight.petproject.dto.annotation.UsernameValidation;

public class UpdateUserRequest {
    @UsernameValidation
    private String username;

    @EmailValidation
    private String email;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(
            String username,
            String email
    ) {
        this.username = username;
        this.email = email;
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
}
