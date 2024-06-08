package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
    Optional<Historial> findByUserId(Long userId);
}
