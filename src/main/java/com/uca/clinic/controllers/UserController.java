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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.clinic.responses.GeneralResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping("/findall") //PROBADO.
    public ResponseEntity<GeneralResponse> findAllUsers(){

        return GeneralResponse.getResponse(HttpStatus.OK, "succesfully request");
    }

    @GetMapping("/findById") //PROBADO.
    public ResponseEntity<GeneralResponse> findUserByID(@RequestParam("userID") UUID userID){

        return GeneralResponse.getResponse(HttpStatus.OK, "succesfully request. UUID Received: " + userID);
    }

    @GetMapping("/findByRole") //PROBADO.
    public ResponseEntity<GeneralResponse> findAllByRole(@RequestParam("Role") UUID Role){
        //Explicación: Muestra todos los usuarios que cuentan con el rol solicitado.

        return GeneralResponse.getResponse(HttpStatus.OK, "succesfully request. ROLE sent: " + Role);
    }
     
    @PostMapping("/create") //PROBADO.
    public ResponseEntity<GeneralResponse> createUser(@RequestBody Object newUserDTO){

        return GeneralResponse.getResponse(HttpStatus.OK, "Create was succesfully. Data received: ", newUserDTO);
    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<GeneralResponse> deleteUser(@RequestParam("userID") UUID userID){

        return GeneralResponse.getResponse(HttpStatus.OK, "User was deleted successfully", userID);
    }

    @PutMapping("/addrole") //PROBADO.
    public ResponseEntity<GeneralResponse> addRoleToUser(@RequestBody Object userNewRoleDTO){
        /*Explicación:
         * Este método sirve para añadir un nuevo rol a un usuario, pues los usuarios pueden
           tener más de un rol en la aplicación.
         */
        
        return GeneralResponse.getResponse(HttpStatus.OK, "Role adding was succesfully", userNewRoleDTO);
    }

    @PutMapping("/removerole") //PROBADO.
    public ResponseEntity<GeneralResponse> removeRoleToUser(@RequestBody Object RemoveUserRoleDTO){
         /*Explicación:
         * Este método sirve para añadir un nuevo rol a un usuario, pues los usuarios pueden
           tener más de un rol en la aplicación.
         */
        return GeneralResponse.getResponse(HttpStatus.OK, "Role remove was succesfully", RemoveUserRoleDTO);
    }
}
