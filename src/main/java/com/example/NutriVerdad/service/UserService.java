package com.example.NutriVerdad.service;

import com.example.NutriVerdad.dto.RegisterRequest;
import com.example.NutriVerdad.dto.UserResponse;
import com.example.NutriVerdad.entity.User;
import com.example.NutriVerdad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Este correo ya está registrado.";
        }

        User user = User.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol("USER")
                .nivel(1)
                .build();

        userRepository.save(user);
        return "Usuario registrado correctamente.";
    }

    public UserResponse getCurrentUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByEmail(email)
                .map(user -> new UserResponse(user.getId(), user.getNombre(), user.getEmail(), user.getRol(), user.getNivel()))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
    }
}
