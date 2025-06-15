package com.example.NutriVerdad.service;

import com.example.NutriVerdad.dto.Minijuego.NivelDTO;
import com.example.NutriVerdad.dto.Minijuego.ProgresoDTO;
import com.example.NutriVerdad.entity.*;
import com.example.NutriVerdad.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NivelService {
    private final NivelRepository nivelRepo;
    private final ProgresoRepository progresoRepo;
    private final UserRepository userRepo;

    public List<NivelDTO> listarNiveles() {
        return nivelRepo.findAll().stream().map(n -> {
            NivelDTO dto = new NivelDTO();
            dto.setId(n.getId());
            dto.setTitulo(n.getTitulo());
            dto.setDescripcion(n.getDescripcion());
            dto.setDificultad(n.getDificultad());
            return dto;
        }).collect(Collectors.toList());
    }

    public void guardarProgreso(ProgresoDTO dto) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User usuario = userRepo.findByEmail(email).orElseThrow();

        Nivel nivel = nivelRepo.findById(dto.getNivelId()).orElseThrow();

        Progreso progreso = Progreso.builder()
                .usuario(usuario)
                .nivel(nivel)
                .puntaje(dto.getPuntaje())
                .build();

        progresoRepo.save(progreso);
    }

    public List<ProgresoDTO> obtenerProgresoUsuarioActual() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User usuario = userRepo.findByEmail(email).orElseThrow();

        return progresoRepo.findByUsuario(usuario).stream().map(p -> {
            ProgresoDTO dto = new ProgresoDTO();
            dto.setNivelId(p.getNivel().getId());
            dto.setPuntaje(p.getPuntaje());
            return dto;
        }).collect(Collectors.toList());
    }
}
