package com.ltifinance.loanapp.loanservice.security.controller;

import com.ltifinance.loanapp.loanservice.security.dto.RegisterRequest;
import com.ltifinance.loanapp.loanservice.security.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {

        authService.register(
                request.getUsername(),
                request.getPassword());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequest request) {
        System.out.println("login controller reached");

        String token =authService.login(request.getUsername(),request.getPassword());

        return ResponseEntity.ok(token);
    }
}