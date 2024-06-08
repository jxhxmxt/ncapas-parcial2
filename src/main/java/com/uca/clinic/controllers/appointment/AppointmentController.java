package com.uca.clinic.controllers.appointment;


import com.uca.clinic.domain.dtos.CitaMedicaDto;
import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.CitaMedicaService;
import com.uca.clinic.services.UserService;
import com.uca.clinic.utils.EstadoCita;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final UserService userService;

    private final CitaMedicaService citaMedicaService;

    @Autowired
    public AppointmentController(UserService userService, CitaMedicaService citaMedicaService){
        this.userService = userService;
        this.citaMedicaService = citaMedicaService;
    }

    @RolesAllowed({"ROLE_MEDICO", "ROLE_ADMIN"})
    @PermitAll
    @PostMapping("/request")

    public ResponseEntity<GeneralResponse> requestAppointment(@AuthenticationPrincipal User userDetails,@RequestBody @Valid CitaMedicaDto citaMedicaDto){
        User _user = userService.findById(userDetails.getId());


        return GeneralResponse.getResponse(HttpStatus.CREATED, "Success", citaMedicaService.save(_user, citaMedicaDto));
    }


    @RolesAllowed({"ROLE_ASISTENTE"})

    @PostMapping("/approve")
    public ResponseEntity<GeneralResponse> approveAppointment(){


        return GeneralResponse.getResponse("Appointment approved");

    }


    @RolesAllowed({"ROLE_PACIENTE", "ROLE_ADMIN"})
    @GetMapping("/own")
    public ResponseEntity<GeneralResponse> getOwnAppointments(@AuthenticationPrincipal User userDetails, @RequestParam(required = false) String status){
        User _user = userService.findById(userDetails.getId());


        EstadoCita estadoCita = EstadoCita.valueOf(status);


        List<CitaMedica> citas = citaMedicaService.findByUserAndEstado(_user, estadoCita);

        return GeneralResponse.getResponse(HttpStatus.OK, "Success", citas);

    }


}
