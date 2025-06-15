package com.example.NutriVerdad.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;

    private String rol; // Por ejemplo: "USER" o "ADMIN"

    @Column(name = "nivel", nullable = false)
    private int nivel = 1;
}
