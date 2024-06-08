package com.uca.clinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uca.clinic.domain.entities.CitaMedica;

import java.util.List;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long>{

 CitaMedica findByPacienteId(Long userId);

    List<CitaMedica> findByEstado(String estado);


    List<CitaMedica> findAllByEstadoIsLikeIgnoreCase(String estado);
}
