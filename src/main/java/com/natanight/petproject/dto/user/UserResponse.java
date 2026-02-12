package com.natanight.petproject.dto.user;

import com.natanight.petproject.models.User;

public class UserResponse {
    private final Long id;
    private final String username;
    private final String email;
    private final Boolean isAdmin;

    public UserResponse(
            Long id,
            String username,
            String email,
            Boolean isAdmin
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public static UserResponse fromEntity(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isAdmin()
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

    public Boolean getAdmin() {
        return isAdmin;
    }
}
