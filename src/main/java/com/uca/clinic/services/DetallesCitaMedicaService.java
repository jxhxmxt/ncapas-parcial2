package com.uca.clinic.services;

import com.uca.clinic.domain.entities.DetallesCitaMedica;

import java.util.List;

public interface DetallesCitaMedicaService {
    List<DetallesCitaMedica> findAll();
    DetallesCitaMedica findById(Long id);
    DetallesCitaMedica save(DetallesCitaMedica detallesCitaMedica);
    void deleteById(Long id);
}
