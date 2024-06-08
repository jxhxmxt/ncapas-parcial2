package com.uca.clinic.controllers.asistente;



import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.CitaMedicaService;
import com.uca.clinic.services.DetallesCitaMedicaService;
import com.uca.clinic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asistente")
public class AsistenteController {
    private final CitaMedicaService citaMedicaService;
    private final DetallesCitaMedicaService detallesCitaMedicaService;

    private final UserService userService;

    @Autowired
    public AsistenteController(CitaMedicaService citaMedicaService, DetallesCitaMedicaService detallesCitaMedicaService, UserService userService){
        this.citaMedicaService = citaMedicaService;
        this.detallesCitaMedicaService = detallesCitaMedicaService;
        this.userService = userService;
    }

    @GetMapping("/appointments")
    public ResponseEntity<GeneralResponse> getAppointmentsByStatus(@RequestParam String estado){
        return GeneralResponse.getResponse(HttpStatus.OK, estado, citaMedicaService.findAllByEstado(estado));
    }






}
