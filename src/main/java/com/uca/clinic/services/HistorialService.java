package com.uca.clinic.services;

import com.uca.clinic.domain.dtos.HistorialDto;
import com.uca.clinic.domain.entities.Historial;
import com.uca.clinic.domain.entities.User;

import java.util.List;

public interface HistorialService {
    List<Historial> findAll();
    Historial findById(Long id);
    Historial findByPatientId(Long patientId);
    Historial save(User userDetails, HistorialDto historial);
    void deleteById(Long id);
}
