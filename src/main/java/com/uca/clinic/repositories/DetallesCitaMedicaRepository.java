package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.DetallesCitaMedica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesCitaMedicaRepository extends JpaRepository<DetallesCitaMedica, Long> {
    List<DetallesCitaMedica> findByCitaMedicaId(Long citaMedicaId);
    List<DetallesCitaMedica> findByEspecialidadId(Long specialtyId);
    List<DetallesCitaMedica> findByMedico(Long doctorId);
}
