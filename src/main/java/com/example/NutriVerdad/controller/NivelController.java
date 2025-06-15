package com.example.NutriVerdad.controller;

import com.example.NutriVerdad.dto.Minijuego.ProgresoDTO;
import com.example.NutriVerdad.dto.Minijuego.NivelDTO;
import com.example.NutriVerdad.service.NivelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;

@RestController
@RequestMapping("/api/niveles")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://nutri-verdad-8yis.vercel.app")
public class NivelController {
    private final NivelService nivelService;

    @GetMapping
    public List<NivelDTO> listar() {
        return nivelService.listarNiveles();
    }

    @PostMapping("/progreso")
    public void guardarProgreso(@RequestBody ProgresoDTO dto) {
        nivelService.guardarProgreso(dto);
    }

    @GetMapping("/progreso")
    public List<ProgresoDTO> obtenerMiProgreso() {
        return nivelService.obtenerProgresoUsuarioActual();
    }
}
