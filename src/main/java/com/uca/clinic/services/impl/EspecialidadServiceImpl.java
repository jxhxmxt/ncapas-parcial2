package com.uca.clinic.services.impl;

import com.uca.clinic.domain.entities.Especialidad;
import com.uca.clinic.repositories.EspecialidadRepository;
import com.uca.clinic.services.EspecialidadService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadServiceImpl(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public List<Especialidad> findAll() {
        return especialidadRepository.findAll();
    }

    @Override
    public Especialidad findById(String id) {
        return especialidadRepository.findById(id).orElse(null);
    }



    @Override
    public Especialidad save(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @Override
    public void deleteById(String id) {
        especialidadRepository.deleteById(id);
    }
}
