package com.natanight.petproject.controller;

import com.natanight.petproject.dto.auth.AuthRequest;
import com.natanight.petproject.dto.auth.AuthResponse;
import com.natanight.petproject.service.AuthService;
import com.natanight.petproject.service.JwtService;
import org.jspecify.annotations.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(
            JwtService jwtService,
            AuthService authService
    ) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @NonNull AuthRequest request) {
        authService.authenticate(request.getUsername(), request.getPassword());
        String token = jwtService.generateToken(request.getUsername());
        return new AuthResponse(token);
    }
}