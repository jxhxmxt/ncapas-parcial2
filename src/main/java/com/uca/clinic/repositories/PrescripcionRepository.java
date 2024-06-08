package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.Prescripcion;
import com.uca.clinic.domain.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescripcionRepository extends JpaRepository<Prescripcion, Long> {
    List<Prescripcion> findByCitaMedicaId(Long citaMedicaId);

    List<Prescripcion> findByCitaMedica(CitaMedica citaMedica);


    @Query("SELECT p FROM Prescripcion p WHERE p.citaMedica.paciente.id = :userId")
    List<Prescripcion> findByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Prescripcion p WHERE p.citaMedica.paciente = :user")
    List<Prescripcion> findByUser(User user);
}
