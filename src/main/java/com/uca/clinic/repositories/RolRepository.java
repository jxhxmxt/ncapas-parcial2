package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    boolean existsByNombre(String roleName);
}
