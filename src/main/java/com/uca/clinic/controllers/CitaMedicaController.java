package com.uca.clinic.controllers;

import java.util.UUID;

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
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.domain.dtos.CitaMedicaDto;
import com.uca.clinic.domain.entities.User;
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
    public ResponseEntity<GeneralResponse> createCitaMedica(@AuthenticationPrincipal User userDetails, @RequestBody CitaMedicaDto citaMedica){
        return GeneralResponse.getResponse(HttpStatus.OK, citaMedicaService.save(userDetails, citaMedica));
    }

    @GetMapping("/appointment")
    public ResponseEntity<GeneralResponse> findCitaById(Long citaId){
        return GeneralResponse.getResponse(HttpStatus.OK, citaMedicaService.findById(citaId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<GeneralResponse> findCitasByUsuario(@PathVariable("userId") Long userId){
        return GeneralResponse.getResponse(HttpStatus.OK, citaMedicaService.findByPacienteId(userId));
    }

    @GetMapping("/doctor/{userId}")
    public ResponseEntity<GeneralResponse> findCitasByDoctor(@PathVariable("userId") Long userId){
        // TODO: This method should exist in the DetallesCitaMedicaService
        return GeneralResponse.getResponse(HttpStatus.OK, "The request was sent successfully");
    }

    @PutMapping("/changestatus")
    public ResponseEntity<GeneralResponse> changeAppnmtStatus(Long id, String newStatus){
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, citaMedicaService.changeStatus(id, newStatus));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse> deleteAppnmt(Long id){
        citaMedicaService.deleteById(id);
        return GeneralResponse.getResponse(HttpStatus.OK, "Appointment deleted successfully");
    }

}
