package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.User;
import com.uca.clinic.utils.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uca.clinic.domain.entities.CitaMedica;

import java.util.List;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long>{

 CitaMedica findByPacienteId(Long userId);

    List<CitaMedica> findAllByEstado(EstadoCita estado);

    List<CitaMedica> findAllByPacienteAndEstado(User user, EstadoCita estado);

    List<CitaMedica> findAllByPaciente(User user);
}
