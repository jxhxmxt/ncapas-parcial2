package com.uca.clinic.services;

import com.uca.clinic.domain.entities.Especialidad;

import java.util.List;

public interface EspecialidadService {
    List<Especialidad> findAll();
    Especialidad findById(Long id);
    Especialidad save(Especialidad especialidad);
    void deleteById(Long id);
}
