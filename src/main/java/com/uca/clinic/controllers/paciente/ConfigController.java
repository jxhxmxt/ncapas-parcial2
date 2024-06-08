package com.uca.clinic.controllers.paciente;


import com.uca.clinic.domain.entities.Rol;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.RolService;
import com.uca.clinic.services.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final UserService userService;

    private final RolService rolService;

    @Autowired
    public ConfigController(UserService userService, RolService rolService) {
        this.userService = userService;
        this.rolService = rolService;
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("/user-rol")
    ResponseEntity<GeneralResponse> assignRoleToUser(@RequestParam String identifier, @RequestParam String roleName){



        User _user = userService.findOneByIdentifier(identifier);
        if (_user == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found");
        }

        Rol _rol = rolService.findByNombre(roleName);

        if (_rol == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND,"Role not found");
        }

        if (_rol.getNombre().equals("ADMIN") || _rol.getNombre().equals("PACIENTE")) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "You can't assign this role");
        }


        if (_user.getRoles().contains(_rol)) {
//            TOGGLE ROLE
            userService.removeRole(_user, _rol);
            return GeneralResponse.getResponse("Role removed successfully");
        }
        try {
            userService.assignRole(_user, _rol);
            return GeneralResponse.getResponse("Role assigned successfully");
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }
}
