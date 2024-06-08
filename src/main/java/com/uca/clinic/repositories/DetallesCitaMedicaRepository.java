package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.domain.entities.User;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesCitaMedicaRepository extends JpaRepository<DetallesCitaMedica, Long> {
    List<DetallesCitaMedica> findByCitaMedicaId(Long citaMedicaId);
    List<DetallesCitaMedica> findByEspecialidadId(String specialtyId);
    List<DetallesCitaMedica> findByMedico(User doctor);

    @Query("SELECT dc FROM DetallesCitaMedica dc WHERE dc.medico = :medico AND dc.citaMedica.fecha BETWEEN :from AND :to")
    List<DetallesCitaMedica> findByMedicoAndFechaBetween(User medico, Date from, Date to);

}
