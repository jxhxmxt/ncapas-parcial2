package com.uca.clinic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.DetallesCitaMedicaService;

@RestController
@RequestMapping("/api/appointments/details")
public class DetallesCitaMedicaController {

 private final DetallesCitaMedicaService detallesCitaMedicaService;

 public DetallesCitaMedicaController(DetallesCitaMedicaService detallesCitaMedicaService) {
  this.detallesCitaMedicaService = detallesCitaMedicaService;
 }

 @GetMapping("/allByAppointment/")
 public ResponseEntity<GeneralResponse> findAllByAppointmentId(@RequestParam("appointmentId") Long appointmentId){
  return GeneralResponse.getResponse(HttpStatus.OK, detallesCitaMedicaService.findByCitaMedicaId(appointmentId));
 }

 @GetMapping("/allBySpecialty/")
 public ResponseEntity<GeneralResponse> findAllBySpecialtyId(@RequestParam("specialtyId") Long specialtyId){
  return GeneralResponse.getResponse(HttpStatus.OK, detallesCitaMedicaService.findBySpecialtyId(specialtyId));
 }

 @GetMapping("/allByDoctor/")
 public ResponseEntity<GeneralResponse> findAllByDoctorId(@RequestParam("doctorId") Long doctorId){
  return GeneralResponse.getResponse(HttpStatus.OK, detallesCitaMedicaService.findByDoctorId(doctorId));
 }

 @PostMapping("/create")
 public ResponseEntity<GeneralResponse> createDetallesCitaMedica(@RequestBody DetallesCitaMedica detallesCitaMedica){
  return GeneralResponse.getResponse(HttpStatus.OK, detallesCitaMedicaService.save(detallesCitaMedica));
 }

 

}
