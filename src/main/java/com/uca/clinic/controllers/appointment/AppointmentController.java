package com.uca.clinic.controllers.appointment;


import com.uca.clinic.domain.dtos.CitaMedicaDto;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.CitaMedicaService;
import com.uca.clinic.services.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
