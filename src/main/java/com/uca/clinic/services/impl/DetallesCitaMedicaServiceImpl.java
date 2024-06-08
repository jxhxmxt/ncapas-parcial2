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
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
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
    @Transactional
    public DetallesCitaMedica scheduleAppointment(CitaMedica cita, User medico, Especialidad especialidad, Date fecha) {

        if(cita.getPaciente().getRoles() == null){
            User _paciente = userService.findById(cita.getPaciente().getId());
            _paciente.getRoles().add(rolService.findByNombre("PACIENTE"));
            userService.save(_paciente);
        }

        if(!medico.isMedico()){
            throw new RuntimeException("User is not a doctor");
        }


//        Compare if the doctor is aviable for the date + 1 hour

        List<DetallesCitaMedica> citas = detallesCitaMedicaRepository.findByMedico(medico);

//        log.info("Citas: {}", citas);

            for(DetallesCitaMedica citaMedica : citas){
                Date from = citaMedica.getCitaMedica().getFecha();

                Date to = Date.from(citaMedica.getCitaMedica().getFecha().toInstant().plusSeconds(3600));

                log.info("Cita de medico: {}", citaMedica.getCitaMedica().getFecha());
                log.info("Cita desde: {}", from);
                log.info("Cita hasta: {}", to);
                log.info("Fecha a comparar: {}", fecha);


                if(fecha.after(from) && fecha.before(to)){
                    throw new RuntimeException("Doctor is not available for the date");
                }
            }

//        DetallesCitaMedica detallesCitaMedica = DetallesCitaMedica.builder()
//                .citaMedica(cita)
//                .medico(medico)
//                .especialidad(especialidad)
//                .build();

        DetallesCitaMedica detallesCitaMedica = new DetallesCitaMedica();
        detallesCitaMedica.setCitaMedica(cita);
        detallesCitaMedica.setMedico(medico);
        detallesCitaMedica.setEspecialidad(especialidad);



        CitaMedica _cita = citaMedicaService.findById(cita.getId());
        _cita.setFecha( fecha);

        _cita.setEstado("Agendada");

        return detallesCitaMedicaRepository.save(detallesCitaMedica);

    }

}
