package com.uca.clinic.services.impl;

import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.Prescripcion;
import com.uca.clinic.domain.entities.User;

import com.uca.clinic.repositories.PrescripcionRepository;
import com.uca.clinic.services.PrescripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class PrescripcionServiceImpl implements PrescripcionService {

    private final PrescripcionRepository prescripcionRepository;


    @Autowired
    public PrescripcionServiceImpl(PrescripcionRepository prescripcionRepository) {
        this.prescripcionRepository = prescripcionRepository;
    }


    @Override
    public Prescripcion create(Prescripcion prescripcion) {
        return prescripcionRepository.save(prescripcion);
    }

    @Override
    public Prescripcion findById(Long id) {
        return prescripcionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Prescripcion> findAll() {
        return prescripcionRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        prescripcionRepository.deleteById(id);
    }

    @Override
    public void delete(Prescripcion prescripcion) {
        prescripcionRepository.delete(prescripcion);
    }

    @Override
    public Prescripcion update(Prescripcion prescripcion) {
        return prescripcionRepository.save(prescripcion);
    }

    @Override
    public List<Prescripcion> findByCitaMedicaId(Long citaMedicaId) {
        return prescripcionRepository.findByCitaMedicaId(citaMedicaId);
    }

    @Override
    public List<Prescripcion> findByCitaMedica(CitaMedica citaMedica) {
        return prescripcionRepository.findByCitaMedica(citaMedica);
    }


    @Override
    public List<Prescripcion> findByUserId(Long userId) {
        return prescripcionRepository.findByUserId(userId);
    }

    @Override
    public List<Prescripcion> findByUser(User user) {
        return prescripcionRepository.findByUser(user);
    }


}
