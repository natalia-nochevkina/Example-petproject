package com.natanight.petproject.controller;

import com.natanight.petproject.dto.auth.AuthRequest;
import com.natanight.petproject.dto.auth.AuthResponse;
import com.natanight.petproject.service.JwtService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @NonNull AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = Optional.ofNullable(auth.getPrincipal())
                .filter(UserDetails.class::isInstance)
                .map(UserDetails.class::cast)
                .orElseThrow(() -> new IllegalStateException("Authentication principal missing"));
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }
}