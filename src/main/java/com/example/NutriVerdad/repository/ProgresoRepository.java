package com.example.NutriVerdad.repository;

import com.example.NutriVerdad.entity.Progreso;
import com.example.NutriVerdad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgresoRepository extends JpaRepository<Progreso, Long>{
    List<Progreso> findByUsuario(User usuario);
}
