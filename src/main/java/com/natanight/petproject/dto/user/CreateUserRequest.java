package com.natanight.petproject.dto.user;

import com.natanight.petproject.dto.annotation.EmailValidation;
import com.natanight.petproject.dto.annotation.PasswordValidation;
import com.natanight.petproject.dto.annotation.UsernameValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequest {
    @NotBlank
    @UsernameValidation
    private String username;

    @NotBlank
    @EmailValidation
    private String email;

    @NotBlank
    @PasswordValidation
    private String password;

    public CreateUserRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
