package com.uca.clinic.controllers;

import java.util.List;
import java.util.UUID;

import com.uca.clinic.domain.dtos.PrescripcionDTO;
import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.Prescripcion;
import com.uca.clinic.services.CitaMedicaService;
import com.uca.clinic.services.PrescripcionService;
import com.uca.clinic.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uca.clinic.responses.GeneralResponse;

@RestController
@RequestMapping("/api/prescripcion")
public class PrescripcionController {

    private final PrescripcionService prescripcionService;
   private final CitaMedicaService citaMedicaService;

    @Autowired
    public PrescripcionController(PrescripcionService prescripcionService, CitaMedicaService citaMedicaService){
        this.prescripcionService = prescripcionService;
        this.citaMedicaService = citaMedicaService;
    }


    @GetMapping("")
    public ResponseEntity<GeneralResponse> findAllPrescriptions(){
        return GeneralResponse.getResponse(HttpStatus.OK,"success", prescripcionService.findAll());
    }

    @PostMapping("/{cita_id}")
    public ResponseEntity<GeneralResponse> createPrescription(@RequestBody @Valid PrescripcionDTO prescripcionDTO, @PathVariable String cita_id){

        CitaMedica _cita = citaMedicaService.findById(Long.parseLong(cita_id));
        if(_cita == null){
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Appointment not found");
        }
        Prescripcion _prescripcion = new Prescripcion();
        _prescripcion.setMedicamento(prescripcionDTO.getMedicamento());
        _prescripcion.setDosis(prescripcionDTO.getDosis());
        _prescripcion.setCitaMedica(_cita);

        prescripcionService.save(_prescripcion);


        return GeneralResponse.getResponse(HttpStatus.OK, "Prescription was created successfully");
    }
    @PostMapping("/{cita_id}/create-many")
    public ResponseEntity<GeneralResponse> createPrescription(@RequestBody @Valid List<PrescripcionDTO> prescripcionDTOS, @PathVariable String cita_id){

        CitaMedica _cita = citaMedicaService.findById(Long.parseLong(cita_id));

        if(_cita == null){
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Appointment not found");
        }

        for(PrescripcionDTO prescripcionDTO : prescripcionDTOS){
            Prescripcion _prescripcion = new Prescripcion();
            _prescripcion.setMedicamento(prescripcionDTO.getMedicamento());
            _prescripcion.setDosis(prescripcionDTO.getDosis());
            _prescripcion.setCitaMedica(_cita);
            prescripcionService.save(_prescripcion);
        }



        return GeneralResponse.getResponse(HttpStatus.OK, "Prescription was created successfully");
    }


    //Crear prescripción:
    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createCitaMedica(Object newAppmntDTO){
        //Falta: Que reciba un DTO con los datos de la prescripción en un @RequestParam en los args.
        //Llamar al servicio que se encargará de validar los datos, crear y almacenar la prescripcion.
        //Devolver un ResponseEntity con la respuesta correspondiente al cliente.

        return GeneralResponse.getResponse(HttpStatus.OK, "Prescription was created successfuly");
    }

    //Prescripciones por doctor:
    @GetMapping("/findOne")
    public ResponseEntity<GeneralResponse> findPrscByID(UUID prscID){
        /*Falta: Servicio que devuelva una lista que contenga todas las prescripciones que
         tengan el UUID del doctor solicitado en el campo correspondiente al doctor que creó la
         prescipción.
         
         Devolver un RespondseEntity que lleve la respuesta adecuada */

        return GeneralResponse.getResponse(HttpStatus.OK, "Completed Request");
    }


    //Prescripciones por doctor
    @GetMapping("/user")
    public ResponseEntity<GeneralResponse> findPrscByDoctor(UUID doctorID){
        /*Falta: Servicio que devuelva una lista que contenga todas las prescripciones que
         tengan el UUID del doctor solicitado en el campo correspondiente al doctor que creó la
         prescipción.
         
         Devolver un RespondseEntity que lleve la respuesta adecuada */

        return GeneralResponse.getResponse(HttpStatus.OK, "Appointment was created successfuly");
    }
    
    //Prescripciones por cita:
    @GetMapping("/appointment")
    public ResponseEntity<GeneralResponse> findPrscByAppointment(UUID AppointmentID){
        /*Falta: Servicio que devuelva una lista que contenga todas las prescripciones que
         tengan el UUID de la cita solicitada en el campo correspondiente a la cita a la que
         pertenece la prescipción.
         
        Devolver un RespondseEntity que lleve la respuesta adecuada */

        return GeneralResponse.getResponse(HttpStatus.OK, "Request Succesfully",null);
    }

    @PutMapping("/replace")
    public ResponseEntity<GeneralResponse> replacePrescription(@RequestBody Object replacePrescriptionDTO ){
        /*Explicación: En teoría, este controlador debería recibir un DTO con todos los datos
        de una prescripción, incluyendo los que no se van a modificar para simplificar el proceso
        al reemplazar el objeto Prescripción completo y no evitar modificar solo un campo. */

        return GeneralResponse.getResponse(HttpStatus.OK, "Prescription was replaced successfully");
    }

    //Borrar prescripciones:
    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse> deleteAppnmt(UUID presctn){
        return GeneralResponse.getResponse(HttpStatus.OK, "prescription was deleted successfully");
    }
}
