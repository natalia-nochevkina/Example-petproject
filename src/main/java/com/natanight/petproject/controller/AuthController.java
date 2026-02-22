package com.natanight.petproject.controller;

import com.natanight.petproject.dto.auth.AuthRequest;
import com.natanight.petproject.dto.auth.AuthResponse;
import com.natanight.petproject.service.AuthService;
import com.natanight.petproject.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid AuthRequest request) {
        // TODO: maybe makes sense to combine those into one method?
        UserDetails userDetails = authService.getDetails(request);
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }
}