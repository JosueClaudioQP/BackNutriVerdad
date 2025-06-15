package com.example.NutriVerdad.service;

import com.example.NutriVerdad.dto.LoginResponse;
import com.example.NutriVerdad.dto.LoginRequest;
import com.example.NutriVerdad.entity.User;
import com.example.NutriVerdad.repository.UserRepository;
import com.example.NutriVerdad.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        return new LoginResponse(token, user.getNombre());
    }

}
