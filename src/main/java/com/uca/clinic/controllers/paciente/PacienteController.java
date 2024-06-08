package com.uca.clinic.controllers.paciente;


import com.uca.clinic.domain.dtos.CitaMedicaDto;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.CitaMedicaService;
import com.uca.clinic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/paciente")
@RestController
public class PacienteController {


    private final CitaMedicaService citaMedicaService;
    private final UserService userService;

    @Autowired
    public PacienteController(CitaMedicaService citaMedicaService, UserService userService){
        this.citaMedicaService = citaMedicaService;
        this.userService = userService;
    }

    @PostMapping("/appointment")
    public ResponseEntity<GeneralResponse> createAppointment(
            @AuthenticationPrincipal User userDetails,
            @RequestBody CitaMedicaDto citaMedica){
        User _user = userService.findById(userDetails.getId());


        return GeneralResponse.getResponse(citaMedicaService.save(_user, citaMedica));
    }
}
