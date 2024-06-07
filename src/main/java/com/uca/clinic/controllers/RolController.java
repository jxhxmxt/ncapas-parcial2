package com.uca.clinic.controllers;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.responses.GeneralResponse;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    
    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createRole(Object newRoleDTO){
        //Falta: Que reciba un DTO con los datos del rol en un @RequestParam en los args.
        //Llamar al servicio que se encargará de validar los datos, crear y almacenar el rol.
        //Devolver un ResponseEntity con la respuesta correspondiente al cliente.

        return GeneralResponse.getResponse(HttpStatus.OK, "Role was created successfuly");
    }

    @GetMapping("/findAll")
    public ResponseEntity<GeneralResponse> findAllRoles(){
        //TODO: Servicio que devuelva todos los roles almacenados.
        return GeneralResponse.getResponse(HttpStatus.OK, "Roles showed succesfully");
    }

    @PutMapping("/modify")
    public ResponseEntity<GeneralResponse> replaceRoleByID(UUID roleID){
        //TODO: Servicio que reciba un DTO y lo reemplace por otro.
        return GeneralResponse.getResponse(HttpStatus.OK, "Role replaced succesfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse> deleteByID(UUID roleID){
        //TODO: Servicio que reciba un ID de un rol y lo elimine.
        return GeneralResponse.getResponse(HttpStatus.OK, "Role deleted succesfully");
    }
}
