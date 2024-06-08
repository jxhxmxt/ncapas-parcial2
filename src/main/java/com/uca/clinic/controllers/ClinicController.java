package com.uca.clinic.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.DetallesCitaMedicaService;
import com.uca.clinic.services.UserService;

@RestController
@RequestMapping("/clinic")
public class ClinicController {
    private final DetallesCitaMedicaService detallesCitaMedicaService;
    private final UserService userService;

    public ClinicController(DetallesCitaMedicaService detallesCitaMedicaService, UserService userService) {
        this.detallesCitaMedicaService = detallesCitaMedicaService;
        this.userService = userService;
    }

    @GetMapping("/schedule/")
    public ResponseEntity<GeneralResponse> getDoctorSchedule(@RequestParam Long idDoctor, @RequestParam String fecha){
        User doctor = userService.findById(idDoctor);

        List<DetallesCitaMedica> citas = detallesCitaMedicaService.findByDoctor(doctor);

        return GeneralResponse.getResponse(HttpStatus.OK, citas);
    }
}
