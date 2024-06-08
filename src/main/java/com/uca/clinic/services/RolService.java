package com.uca.clinic.services;

import com.uca.clinic.domain.entities.Rol;

public interface RolService {
    Rol findByNombre(String roleName);

    Object getAll();
}
