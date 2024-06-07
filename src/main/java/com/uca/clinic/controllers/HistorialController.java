package com.uca.clinic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.responses.GeneralResponse;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createHistory(Object newHistoryDTO){

        return GeneralResponse.getResponse(HttpStatus.OK, "Create was succesfully ");
    }

    
}
