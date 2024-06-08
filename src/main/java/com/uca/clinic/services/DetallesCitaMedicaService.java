package com.uca.clinic.services;

import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.domain.entities.Especialidad;
import com.uca.clinic.domain.entities.User;

import java.util.List;

public interface DetallesCitaMedicaService {
    List<DetallesCitaMedica> findAll();
    List<DetallesCitaMedica> findByCitaMedicaId(Long citaMedicaId);
    List<DetallesCitaMedica> findBySpecialtyId(String specialtyId);
    List<DetallesCitaMedica> findByDoctor(User doctor);
    DetallesCitaMedica findById(Long id);
    DetallesCitaMedica save(DetallesCitaMedica detallesCitaMedica);
    void deleteById(Long id);


    DetallesCitaMedica scheduleAppointment(CitaMedica cita, User medico, Especialidad especialidad, String fecha);
}
