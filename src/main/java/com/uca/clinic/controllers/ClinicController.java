package com.uca.clinic.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.CitaMedicaService;
import com.uca.clinic.services.DetallesCitaMedicaService;
import com.uca.clinic.services.UserService;

@RestController
@RequestMapping("/api/clinic")
public class ClinicController {
    private final CitaMedicaService citaMedicaService;
    private final DetallesCitaMedicaService detallesCitaMedicaService;
    private final UserService userService;

    public ClinicController(DetallesCitaMedicaService detallesCitaMedicaService, UserService userService,
            CitaMedicaService citaMedicaService) {
        this.citaMedicaService = citaMedicaService;
        this.detallesCitaMedicaService = detallesCitaMedicaService;
        this.userService = userService;
    }

    @GetMapping("/schedule")
    public ResponseEntity<GeneralResponse> getDoctorSchedule(@RequestParam String fecha,
            @AuthenticationPrincipal User currentUser) {
        User doctor = userService.findById(currentUser.getId());

        // Parsear la fecha solicitada
        LocalDate date = LocalDate.parse(fecha);

        // Obtener los detalles de la cita medica del doctor desde la tabla cruzada
        // CitasMedicasXUser
        List<DetallesCitaMedica> detallesCitas = detallesCitaMedicaService.findByDoctor(doctor);

        // Con los ids de las citas medicas, obtener las citas medicas
        List<CitaMedica> citasMedicas = detallesCitas.stream().map(DetallesCitaMedica::getCitaMedica).toList();

        // Filtrar las citas medicas por la fecha solicitada
        List<CitaMedica> citasMedicasFiltradas = citasMedicas.stream().filter(citaMedica -> {
            Instant instant = citaMedica.getFechaSolicitada().toInstant();
            LocalDate citaDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            return citaDate.equals(date);
        }).toList();

        return GeneralResponse.getResponse(HttpStatus.OK, citasMedicasFiltradas);
    }
}
