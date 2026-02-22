package com.natanight.petproject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "username")
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin = false;

    public User() {
    }

    public User(
            String username,
            String password,
            String email
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}