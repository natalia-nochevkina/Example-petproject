package com.natanight.petproject.dto.user;

import com.natanight.petproject.models.User;

public record UserResponse(Long id, String username, String email, Boolean isAdmin) {
    public static UserResponse fromEntity(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isAdmin()
        );
    }
}
