package com.backend.contigo_fiscal.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.backend.contigo_fiscal.domain.User;
import com.backend.contigo_fiscal.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Lombok inyectará UserRepository y PasswordEncoder
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User login(String email, String password) {
        // 1. Buscamos al usuario por email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(password, user.password())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return user;
    }
}