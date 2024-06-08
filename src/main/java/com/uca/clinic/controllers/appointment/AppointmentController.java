package com.uca.clinic.controllers.appointment;


import com.uca.clinic.responses.GeneralResponse;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {


    @RolesAllowed({"ROLE_MEDICO", "ROLE_ADMIN"})
    @PermitAll
    @PostMapping("/request")
    public ResponseEntity<GeneralResponse> requestAppointment(){
        return ResponseEntity.ok(new GeneralResponse("Appointment requested", null));
    }



}
