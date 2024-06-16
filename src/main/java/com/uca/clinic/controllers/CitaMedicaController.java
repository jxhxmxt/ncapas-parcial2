package com.uca.clinic.controllers;

import java.util.List;
import java.util.UUID;

import com.uca.clinic.repositories.UserRepository;
import com.uca.clinic.services.UserService;
import com.uca.clinic.utils.EstadoCita;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.domain.dtos.CitaMedicaDto;
import com.uca.clinic.domain.dtos.DetallesCitaProgramarDTO;
import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.domain.entities.Especialidad;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.CitaMedicaService;
import com.uca.clinic.services.DetallesCitaMedicaService;
import com.uca.clinic.services.EspecialidadService;

@RestController
@RequestMapping("/api/appointments")
public class CitaMedicaController {

    private final CitaMedicaService citaMedicaService;
    private final DetallesCitaMedicaService detallesCitaMedicaService;
    private final UserService userService;
    private final EspecialidadService especialidadService;

    public CitaMedicaController(CitaMedicaService citaMedicaService,
                                DetallesCitaMedicaService detallesCitaMedicaService, UserService userService, EspecialidadService especialidadService) {
        this.citaMedicaService = citaMedicaService;
        this.detallesCitaMedicaService = detallesCitaMedicaService;
        this.userService = userService;
        this.especialidadService = especialidadService;
    }

    //@RolesAllowed({"ROLE_ASISTENTE", "ROLE_ADMIN", "ROLE_MEDICO"})
    @PostMapping("/approve")
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

    @PermitAll
    @PostMapping("/request")
    public ResponseEntity<GeneralResponse> createCitaMedica(@AuthenticationPrincipal User userDetails,
            @RequestBody CitaMedicaDto citaMedica) {

        User _user = userService.findById(userDetails.getId());
        return GeneralResponse.getResponse(HttpStatus.OK, citaMedicaService.save(_user, citaMedica));
    }

    @PermitAll
    @GetMapping("/appointment/")
    public ResponseEntity<GeneralResponse> findCitaById(@RequestParam Long citaId) {
        return GeneralResponse.getResponse(HttpStatus.OK, citaMedicaService.findById(citaId));
    }

    @GetMapping("/user/")
    public ResponseEntity<GeneralResponse> findCitasByUsuario(@RequestParam Long userId) {
        return GeneralResponse.getResponse(HttpStatus.OK, citaMedicaService.findByPacienteId(userId));
    }

    @GetMapping("/doctor/")
    public ResponseEntity<GeneralResponse> findCitasByDoctor(@RequestParam Long userId) {
        User doctor = userService.findById(userId);
        List<DetallesCitaMedica> detallesCitaMedicas = detallesCitaMedicaService.findByDoctor(doctor);
        
        List<CitaMedica> citas = detallesCitaMedicas.stream().map(DetallesCitaMedica::getCitaMedica).toList();

        return GeneralResponse.getResponse(HttpStatus.OK, citas);
    }

    @PutMapping("/changestatus")
    public ResponseEntity<GeneralResponse> changeAppnmtStatus(Long id, EstadoCita newStatus) {
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, citaMedicaService.changeStatus(id, newStatus));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse> deleteAppnmt(Long id) {
        citaMedicaService.deleteById(id);
        return GeneralResponse.getResponse(HttpStatus.OK, "Appointment deleted successfully");
    }

}
