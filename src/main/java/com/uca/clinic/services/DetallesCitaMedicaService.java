package com.uca.clinic.services;

import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.domain.entities.User;

import java.util.List;

public interface DetallesCitaMedicaService {
    List<DetallesCitaMedica> findAll();
    List<DetallesCitaMedica> findByCitaMedicaId(Long citaMedicaId);
    List<DetallesCitaMedica> findBySpecialtyId(Long specialtyId);
    List<DetallesCitaMedica> findByDoctor(User doctor);
    DetallesCitaMedica findById(Long id);
    DetallesCitaMedica save(DetallesCitaMedica detallesCitaMedica);
    void deleteById(Long id);
}
