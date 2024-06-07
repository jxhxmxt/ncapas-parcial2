package com.uca.clinic.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.responses.GeneralResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping("/findall")
    public ResponseEntity<GeneralResponse> findAllUsers(){

        return GeneralResponse.getResponse(HttpStatus.OK, "succesfully request");
    }

    @GetMapping("/findById")
    public ResponseEntity<GeneralResponse> findUserByID(UUID userID){

        return GeneralResponse.getResponse(HttpStatus.OK, "succesfully request");
    }

    @GetMapping("/findByRole")
    public ResponseEntity<GeneralResponse> findAllByRole(UUID Role){
        //Explicación: Muestra todos los usuarios que cuentan con el rol solicitado.

        return GeneralResponse.getResponse(HttpStatus.OK, "succesfully request");
    }
     
    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createUser(Object newUserDTO){

        return GeneralResponse.getResponse(HttpStatus.OK, "Create was succesfully ");
    }

    @PutMapping("/addrole")
    public ResponseEntity<GeneralResponse> addRoleToUser(UUID roleToAddID, UUID userID){
        /*Explicación:
         * Este método sirve para añadir un nuevo rol a un usuario, pues los usuarios pueden
           tener más de un rol en la aplicación.
         */
        return GeneralResponse.getResponse(HttpStatus.OK, "Add was succesfully");
    }

    @PutMapping("/removerole")
    public ResponseEntity<GeneralResponse> removeRoleToUser(UUID roleToRemID, UUID userID){
        /*Explicación:
         * Este método sirve para añadir un nuevo rol a un usuario, pues los usuarios pueden
           tener más de un rol en la aplicación.
         */
        return GeneralResponse.getResponse(HttpStatus.OK, "Remove was succesfully");
    }
}
