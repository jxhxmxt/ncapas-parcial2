package com.uca.clinic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.responses.GeneralResponse;

@RestController
@RequestMapping("/clinic")
public class ClinicController {
    @GetMapping("/schedule/{id_doctor}/{fecha}")
    public ResponseEntity<GeneralResponse> getDoctorSchedule(@PathVariable("id_doctor") String doctorUsername, @PathVariable("fecha") String fecha){
        
        return GeneralResponse.getResponse(doctorUsername);
    }
}
