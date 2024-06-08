package com.uca.clinic.controllers.asistente;



import com.uca.clinic.domain.dtos.DetallesCitaProgramarDTO;
import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.Especialidad;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.CitaMedicaService;
import com.uca.clinic.services.DetallesCitaMedicaService;
import com.uca.clinic.services.EspecialidadService;
import com.uca.clinic.services.UserService;
import com.uca.clinic.utils.EstadoCita;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/asistente")
public class AsistenteController {
    private final CitaMedicaService citaMedicaService;
    private final DetallesCitaMedicaService detallesCitaMedicaService;

    private final UserService userService;

    private final EspecialidadService especialidadService;

    @Autowired
    public AsistenteController(CitaMedicaService citaMedicaService, DetallesCitaMedicaService detallesCitaMedicaService, UserService userService, EspecialidadService especialidadService){
        this.citaMedicaService = citaMedicaService;
        this.detallesCitaMedicaService = detallesCitaMedicaService;
        this.userService = userService;
        this.especialidadService = especialidadService;
    }

    @GetMapping("/appointments")
    public ResponseEntity<GeneralResponse> getAppointmentsByStatus(@RequestParam String estado){
        EstadoCita estadoCita = EstadoCita.valueOf(estado);
        return GeneralResponse.getResponse(HttpStatus.OK, estado, citaMedicaService.findAllByEstado(estadoCita));
    }

    @PostMapping("/appointment/schedule")
    public ResponseEntity<GeneralResponse> scheduleAppointment( @RequestBody @Valid DetallesCitaProgramarDTO detallesCitaProgramarDTO){

        User _medico = userService.findById(detallesCitaProgramarDTO.getMedicoId());
        CitaMedica _cita = citaMedicaService.findById(detallesCitaProgramarDTO.getCitaMedicaId());
        Especialidad _especialidad = especialidadService.findById(detallesCitaProgramarDTO.getEspecialidadId());

        if(_medico == null){
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Doctor not found");
        }
        if(_cita == null){
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Appointment not found");
        }
        if(_especialidad == null){
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Specialty not found");
        }




        return GeneralResponse.getResponse(HttpStatus.OK, "Appointment was scheduled successfully", detallesCitaMedicaService.scheduleAppointment(_cita, _medico, _especialidad, detallesCitaProgramarDTO.getFecha()));
    }




}
