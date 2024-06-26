package com.uca.clinic.services;

import com.uca.clinic.domain.dtos.AddHistorialEntryDto;
import com.uca.clinic.domain.dtos.HistorialDto;
import com.uca.clinic.domain.entities.Historial;
import com.uca.clinic.domain.entities.User;

import java.util.Date;
import java.util.List;

public interface HistorialService {
    List<Historial> findAll();
    List<Historial> findUserHistorialWithinDateRange(Long userId, Date startDate, Date endDate);
    Historial findById(Long id);
    //Historial findByPatientId(Long patientId);
    Historial save(User userDetails, HistorialDto historial);
    void deleteById(Long id);


    // POST PRE-EXAM
    Historial addHistorialEntry(User doctorOrAssistant, AddHistorialEntryDto addHistorialEntryDto);
}