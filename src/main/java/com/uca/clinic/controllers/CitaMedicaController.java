package com.uca.clinic.controllers;

import java.util.List;
import java.util.UUID;

import com.uca.clinic.repositories.UserRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.domain.dtos.CitaMedicaDto;
import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.CitaMedicaService;
import com.uca.clinic.services.DetallesCitaMedicaService;

@RestController
@RequestMapping("/api/appointments")
public class CitaMedicaController {

    private final CitaMedicaService citaMedicaService;
    private final DetallesCitaMedicaService detallesCitaMedicaService;
    private final UserRepository userRepository;

    public CitaMedicaController(CitaMedicaService citaMedicaService,
                                DetallesCitaMedicaService detallesCitaMedicaService, UserRepository userRepository) {
        this.citaMedicaService = citaMedicaService;
        this.detallesCitaMedicaService = detallesCitaMedicaService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createCitaMedica(@AuthenticationPrincipal User userDetails,
            @RequestBody CitaMedicaDto citaMedica) {
        return GeneralResponse.getResponse(HttpStatus.OK, citaMedicaService.save(userDetails, citaMedica));
    }

    @GetMapping("/appointment/")
    public ResponseEntity<GeneralResponse> findCitaById(@RequestParam Long citaId) {
        return GeneralResponse.getResponse(HttpStatus.OK, citaMedicaService.findById(citaId));
    }

    @GetMapping("/user/")
    public ResponseEntity<GeneralResponse> findCitasByUsuario(@RequestParam Long userId) {
        return GeneralResponse.getResponse(HttpStatus.OK, citaMedicaService.findByPacienteId(userId));
    }

    @GetMapping("/doctor/")
    public ResponseEntity<GeneralResponse> findCitasByDoctor(@RequestParam Long userId) {
        User doctor = userRepository.findById(userId).orElse(null);
        List<DetallesCitaMedica> detallesCitaMedicas = detallesCitaMedicaService.findByDoctor(doctor);
        
        List<CitaMedica> citas = detallesCitaMedicas.stream().map(DetallesCitaMedica::getCitaMedica).toList();

        return GeneralResponse.getResponse(HttpStatus.OK, citas);
    }

    @PutMapping("/changestatus")
    public ResponseEntity<GeneralResponse> changeAppnmtStatus(Long id, String newStatus) {
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, citaMedicaService.changeStatus(id, newStatus));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse> deleteAppnmt(Long id) {
        citaMedicaService.deleteById(id);
        return GeneralResponse.getResponse(HttpStatus.OK, "Appointment deleted successfully");
    }

}
