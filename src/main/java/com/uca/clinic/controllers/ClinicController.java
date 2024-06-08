package com.uca.clinic.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.DetallesCitaMedicaService;
import com.uca.clinic.services.UserService;

@RestController
@RequestMapping("/api/clinic")
public class ClinicController {
    private final DetallesCitaMedicaService detallesCitaMedicaService;
    private final UserService userService;

    public ClinicController(DetallesCitaMedicaService detallesCitaMedicaService, UserService userService) {
        this.detallesCitaMedicaService = detallesCitaMedicaService;
        this.userService = userService;
    }

    @GetMapping("/schedule")
    public ResponseEntity<GeneralResponse> getDoctorSchedule(@RequestParam Long idDoctor, @RequestParam String fecha){
        User doctor = userService.findById(idDoctor);

        List<DetallesCitaMedica> detallesCitas = detallesCitaMedicaService.findByDoctor(doctor);

        // filtrar las citas por fecha y crear un listado de citas medicas
        List<CitaMedica> citas = null;

        return GeneralResponse.getResponse(HttpStatus.OK);
    }
}
