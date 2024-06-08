package com.uca.clinic.services.impl;

import com.uca.clinic.domain.entities.Rol;
import com.uca.clinic.repositories.RolRepository;
import com.uca.clinic.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    @Override
    public Rol findByNombre(String roleName) {
        return rolRepository.findByNombre(roleName);
    }

    @Override
    public List<Rol> getAll() {
        return rolRepository.findAll();
    }
}
