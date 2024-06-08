package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, String> {
    boolean existsByNombre(String roleName);
    Rol findByNombre(String roleName);
}
