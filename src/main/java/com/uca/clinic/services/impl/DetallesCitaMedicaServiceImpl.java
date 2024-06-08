package com.uca.clinic.services.impl;

import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.domain.entities.Especialidad;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.repositories.DetallesCitaMedicaRepository;
import com.uca.clinic.services.CitaMedicaService;
import com.uca.clinic.services.DetallesCitaMedicaService;
import com.uca.clinic.services.RolService;
import com.uca.clinic.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class DetallesCitaMedicaServiceImpl implements DetallesCitaMedicaService {

    private final DetallesCitaMedicaRepository detallesCitaMedicaRepository;

    private final UserService userService;
    private final RolService rolService;

    private final CitaMedicaService citaMedicaService;

    public DetallesCitaMedicaServiceImpl(DetallesCitaMedicaRepository detallesCitaMedicaRepository, UserService userService, RolService rolService, CitaMedicaService citaMedicaService) {
        this.detallesCitaMedicaRepository = detallesCitaMedicaRepository;
        this.userService = userService;
        this.rolService = rolService;
        this.citaMedicaService = citaMedicaService;
    }

    @Override
    public List<DetallesCitaMedica> findAll() {
        return detallesCitaMedicaRepository.findAll();
    }

    @Override
    public List<DetallesCitaMedica> findByCitaMedicaId(Long citaMedicaId) {
        return detallesCitaMedicaRepository.findByCitaMedicaId(citaMedicaId);
    }

    @Override
    public List<DetallesCitaMedica> findBySpecialtyId(String specialtyId) {
        return detallesCitaMedicaRepository.findByEspecialidadId(specialtyId);
    }

    @Override
    public List<DetallesCitaMedica> findByDoctor(User doctor) {
        return detallesCitaMedicaRepository.findByMedico(doctor);
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

    @Override
    public DetallesCitaMedica scheduleAppointment(CitaMedica cita, User medico, Especialidad especialidad, String fecha) {

        if(cita.getPaciente().getRoles() == null){
            User _paciente = userService.findById(cita.getPaciente().getId());
            _paciente.getRoles().add(rolService.findByNombre("PACIENTE"));
            userService.save(_paciente);
        }

        if(!medico.isMedico()){
            throw new RuntimeException("User is not a doctor");
        }

        DetallesCitaMedica detallesCitaMedica = DetallesCitaMedica.builder()
                .citaMedica(cita)
                .medico(medico)
                .especialidad(especialidad)
                .build();


        return detallesCitaMedicaRepository.save(detallesCitaMedica);

    }

}
