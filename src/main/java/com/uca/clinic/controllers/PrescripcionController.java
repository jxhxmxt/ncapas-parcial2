package com.uca.clinic.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.responses.GeneralResponse;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescripcionController {

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
