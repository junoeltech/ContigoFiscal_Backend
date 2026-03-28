package com.backend.contigo_fiscal.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.contigo_fiscal.application.service.AuthService;
import com.backend.contigo_fiscal.domain.User;
import com.backend.contigo_fiscal.web.dto.LoginRequestDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") 
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
        try {
        
            User user = authService.login(request.getEmail(), request.getPassword());    
            return ResponseEntity.ok(Map.of(
                "email", user.email(),
                "name", user.name(),
                "role", "ADMIN" 
            ));

        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of(
                "message", e.getMessage()
            ));
        }
    }
}