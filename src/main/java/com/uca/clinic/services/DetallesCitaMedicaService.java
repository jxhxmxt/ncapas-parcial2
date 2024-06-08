package com.uca.clinic.services;

import com.uca.clinic.domain.entities.DetallesCitaMedica;

import java.util.List;

public interface DetallesCitaMedicaService {
    List<DetallesCitaMedica> findAll();
    List<DetallesCitaMedica> findByCitaMedicaId(Long citaMedicaId);
    List<DetallesCitaMedica> findBySpecialtyId(Long specialtyId);
    List<DetallesCitaMedica> findByDoctorId(Long doctorId);
    DetallesCitaMedica findById(Long id);
    DetallesCitaMedica save(DetallesCitaMedica detallesCitaMedica);
    void deleteById(Long id);
}
