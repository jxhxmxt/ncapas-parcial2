package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.DetallesCitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesCitaMedicaRepository extends JpaRepository<DetallesCitaMedica, Long> {
}
