package com.uca.clinic.controllers;

import com.uca.clinic.domain.dtos.HistorialDto;
import com.uca.clinic.domain.entities.Especialidad;
import com.uca.clinic.domain.entities.Historial;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.services.HistorialService;
import com.uca.clinic.services.RolService;
import com.uca.clinic.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.uca.clinic.responses.GeneralResponse;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    private final UserService userService;
    private final RolService rolService;
    private final HistorialService historialService;

    public HistorialController(UserService userService, RolService rolService, HistorialService historialService) {
        this.userService = userService;
        this.rolService = rolService;
        this.historialService = historialService;
    }

    @GetMapping("/")
    public ResponseEntity<GeneralResponse> getAllHistorials(){
        List<Historial> historials = historialService.findAll();
        return GeneralResponse.getResponse(HttpStatus.OK, historials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getHistorialById(@PathVariable Long id){
        Historial historial = historialService.findById(id);
        if (historial != null) {
            return GeneralResponse.getResponse(HttpStatus.OK, historial);
        } else {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Historial no encontrado");
        }
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping("/")
    public ResponseEntity<GeneralResponse> createHistorial(@RequestBody @Valid HistorialDto historialDto, @AuthenticationPrincipal User userDetails){
        // get authenticated user
        User user = userService.findById(userDetails.getId());

        if(user == null){
            return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "Usuario no authorizado");
        }
        Historial historial = historialService.save(user, historialDto);
        return GeneralResponse.getResponse(HttpStatus.CREATED, historial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteHistorial(@PathVariable Long id) {
        historialService.deleteById(id);
        return GeneralResponse.getResponse(HttpStatus.NO_CONTENT, "Historial eliminado");
    }
}