package com.uca.clinic.services.impl;

import com.uca.clinic.domain.dtos.HistorialDto;
import com.uca.clinic.domain.entities.Historial;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.repositories.HistorialRepository;
import com.uca.clinic.services.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistorialServiceImpl implements HistorialService {
    private final HistorialRepository historialRepository;

    public HistorialServiceImpl(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    @Override
    public List<Historial> findAll() {
        return historialRepository.findAll();
    }

    @Override
    public Historial findById(Long id) {
        return historialRepository.findById(id).orElse(null);
    }

    //@Override
    //public Historial findByPatientId(Long patientId) {
    //    Optional<Historial> historial = historialRepository.findByUserId(patientId);
    //    return historial.orElse(null);
    //}

    @Override
    public Historial save(User userDetails, HistorialDto historialDto) {
        Historial historial = new Historial();
        historial.setUser(userDetails);
        historial.setDetails(historialDto.getDetails());
        historial.setDate(historialDto.getDate());
        return historialRepository.save(historial);
    }

    @Override
    public void deleteById(Long id) {
        historialRepository.deleteById(id);
    }

    @Override
    public List<Historial> findUserHistorialWithinDateRange(Long userId, Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            return historialRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, startDate, endDate);
        } else if (startDate != null) {
            return historialRepository.findByUserIdAndDateAfterOrderByDateDesc(userId, startDate);
        } else if (endDate != null) {
            return historialRepository.findByUserIdAndDateBeforeOrderByDateDesc(userId, endDate);
        } else {
            return historialRepository.findByUserIdOrderByDateDesc(userId);
        }
    }
}