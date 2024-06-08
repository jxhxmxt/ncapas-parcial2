package com.uca.clinic.controllers;

import com.uca.clinic.domain.entities.Especialidad;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.EspecialidadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public ResponseEntity<GeneralResponse> getAllEspecialidades() {
        return GeneralResponse.getResponse(HttpStatus.OK, especialidadService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getEspecialidadById(@PathVariable String id) {
        Especialidad especialidad = especialidadService.findById(id);
        if (especialidad != null) {
            return GeneralResponse.getResponse(HttpStatus.OK, especialidad);
        } else {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Especialidad no encontrada");
        }
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> createEspecialidad(@RequestBody @Valid Especialidad especialidad) {
        return GeneralResponse.getResponse(HttpStatus.CREATED, especialidadService.save(especialidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateEspecialidad(@PathVariable String id, @RequestBody @Valid Especialidad especialidad) {
        Especialidad existingEspecialidad = especialidadService.findById(id);
        if (existingEspecialidad != null) {
            return GeneralResponse.getResponse(HttpStatus.OK, especialidadService.save(especialidad));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteEspecialidad(@PathVariable String id) {
        Especialidad existingEspecialidad = especialidadService.findById(id);
        if (existingEspecialidad != null) {
            especialidadService.deleteById(id);
            return GeneralResponse.getResponse(HttpStatus.OK, "Especialidad eliminada");
        } else {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Especialidad no encontrada");
        }
    }
}
