package com.uca.clinic.controllers;


import com.uca.clinic.domain.entities.Rol;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.RolService;
import com.uca.clinic.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private final UserService userService;
    private final RolService rolService;


    @Autowired
    public AdminController(UserService userService, RolService rolService) {
        this.userService = userService;
        this.rolService = rolService;
    }


    @PostMapping("/assign-role")
    public ResponseEntity<GeneralResponse> assignRoleToUser(@RequestParam String email, @RequestParam String roleName){

        User _user = userService.findByEmail(email);
        if (_user == null) {
            return GeneralResponse.getResponse("User not found", null);
        }

        Rol _rol = rolService.findByNombre(roleName);

        if (_rol == null) {
            return GeneralResponse.getResponse("Role not found", null);
        }


        if (_user.getRoles().contains(_rol)) {
            return GeneralResponse.getResponse("User already has this role", null);
        }
        try {
            userService.assignRole(_user, _rol);
            return GeneralResponse.getResponse("Role assigned successfully", null);
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }
}
