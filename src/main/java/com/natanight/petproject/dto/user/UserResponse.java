package com.natanight.petproject.dto.user;

import com.natanight.petproject.models.User;

public class UserResponse {
    private Long id;
    private String username;
    private String email;

    public UserResponse() {
    }

    public UserResponse(
            Long id,
            String username,
            String email
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static UserResponse fromEntity(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
