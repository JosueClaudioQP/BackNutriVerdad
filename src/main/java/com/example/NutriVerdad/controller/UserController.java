package com.example.NutriVerdad.controller;

import com.example.NutriVerdad.dto.UserResponse;
import com.example.NutriVerdad.entity.User;
import com.example.NutriVerdad.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.NutriVerdad.repository.UserRepository;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://nutri-verdad-8yis.vercel.app")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserResponse getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PatchMapping("/nivel")
    public ResponseEntity<?> actualizarNivel(@RequestBody Map<String, Integer> body) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(email).orElseThrow();
        int nuevoNivel = body.get("nivel");

        if (nuevoNivel > user.getNivel()) {
            user.setNivel(nuevoNivel);
            userRepository.save(user);  // ¡IMPORTANTE!
            System.out.println("✅ Nivel actualizado a " + nuevoNivel);
        } else {
            System.out.println("⚠️ Nivel NO actualizado. Actual: " + user.getNivel() + ", Nuevo: " + nuevoNivel);
        }

        return ResponseEntity.ok().build();
    }
}
