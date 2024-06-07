package com.uca.clinic.services.impl;

import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.repositories.DetallesCitaMedicaRepository;
import com.uca.clinic.services.DetallesCitaMedicaService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class DetallesCitaMedicaServiceImpl implements DetallesCitaMedicaService {

    private final DetallesCitaMedicaRepository detallesCitaMedicaRepository;

    public DetallesCitaMedicaServiceImpl(DetallesCitaMedicaRepository detallesCitaMedicaRepository) {
        this.detallesCitaMedicaRepository = detallesCitaMedicaRepository;
    }

    @Override
    public List<DetallesCitaMedica> findAll() {
        return detallesCitaMedicaRepository.findAll();
    }

    @Override
    public DetallesCitaMedica findById(Long id) {
        return detallesCitaMedicaRepository.findById(id).orElse(null);
    }

    @Override
    public DetallesCitaMedica save(DetallesCitaMedica detallesCitaMedica) {
        return detallesCitaMedicaRepository.save(detallesCitaMedica);
    }

    @Override
    public void deleteById(Long id) {
        detallesCitaMedicaRepository.deleteById(id);
    }
}
