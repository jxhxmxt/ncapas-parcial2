package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, String> {
    boolean existsByNombre(String especialidad);
}
