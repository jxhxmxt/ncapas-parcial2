package com.uca.clinic.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.domain.dtos.CitaMedicaDto;
import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.CitaMedicaService;

@RestController
@RequestMapping("/api/appointments")
public class CitaMedicaController {

    private final CitaMedicaService citaMedicaService;

    public CitaMedicaController(CitaMedicaService citaMedicaService) {
        this.citaMedicaService = citaMedicaService;
    }


    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createCitaMedica(@RequestBody CitaMedicaDto citaMedica){
        return GeneralResponse.getResponse(HttpStatus.OK, citaMedicaService.save(citaMedica));
    }

    @GetMapping("/appointment")
    public ResponseEntity<GeneralResponse> findCitaById(UUID citaId){
        return GeneralResponse.getResponse(HttpStatus.OK, "The request was sent successfully");
    }

    @GetMapping("/user")
    public ResponseEntity<GeneralResponse> findCitasByUsuario(UUID userId){
        return GeneralResponse.getResponse(HttpStatus.OK, "The request was sent successfully");
    }

    @GetMapping("/doctor")
    public ResponseEntity<GeneralResponse> findCitasByDoctor(UUID userId){
        return GeneralResponse.getResponse(HttpStatus.OK, "The request was sent successfully");
    }

    @PutMapping("/changestatus")
    public ResponseEntity<GeneralResponse> changeAppnmtStatus(UUID appnmt, String newStatus){
        return GeneralResponse.getResponse(HttpStatus.OK, "The status was changed successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse> deleteAppnmt(UUID appnmt){
        return GeneralResponse.getResponse(HttpStatus.OK, "The Appointment was deleted successfully");
    }

}
