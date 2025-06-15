package com.example.NutriVerdad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
    private int nivel;
}
