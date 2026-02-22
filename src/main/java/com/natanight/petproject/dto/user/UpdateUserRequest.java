package com.natanight.petproject.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.natanight.petproject.dto.annotation.EmailValidation;
import com.natanight.petproject.dto.annotation.UsernameValidation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = false)
public class UpdateUserRequest {
    @UsernameValidation
    private String username;

    @EmailValidation
    private String email;

    public UpdateUserRequest(
            String username,
            String email
    ) {
        this.username = username;
        this.email = email;
    }

}
