package com.uca.clinic.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.responses.GeneralResponse;

@RestController
@RequestMapping("/api/appointments")
public class CitaMedicaController {


    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createCitaMedica(){
        //Falta: Que reciba un DTO con los datos de la cita en un @RequestParam en los args.
        //Llamar al servicio que se encargará de validar los datos, crear y almacenar la cita.
        //Devolver un ResponseEntity con la respuesta correspondiente al cliente.

        return GeneralResponse.getResponse(HttpStatus.OK, "Appointment was created successfuly");
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
