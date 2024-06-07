package com.uca.clinic.services;

import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.Prescripcion;
import com.uca.clinic.domain.entities.User;

import java.util.List;

public interface PrescripcionService {
    Prescripcion create(Prescripcion prescripcion);
    Prescripcion findById(Long id);
    List<Prescripcion> findAll();
    void delete(Long id);

    void delete(Prescripcion prescripcion);
    Prescripcion update(Prescripcion prescripcion);
    List<Prescripcion> findByCitaMedicaId(Long citaMedicaId);
    List<Prescripcion> findByCitaMedica(CitaMedica citaMedica);

    List<Prescripcion> findByUserId(Long userId);
    List<Prescripcion> findByUser(User user);

}
