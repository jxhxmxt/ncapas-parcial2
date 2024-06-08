package com.uca.clinic.controllers;


import com.uca.clinic.domain.entities.Token;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.domain.entities.dto.LoginUserDTO;
import com.uca.clinic.domain.entities.dto.RegisterUserDTO;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.RolService;
import com.uca.clinic.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
   private final UserService userService;
   private final RolService rolService;

    public AuthController(UserService userService, RolService rolService) {
        this.userService = userService;
        this.rolService = rolService;
    }


    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> register(@RequestBody @Valid RegisterUserDTO user){
        User _user = User.builder()
                .username(user.getUsername())
                .nombre(user.getNombre())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        User userSaved = userService.signUp(_user);

        try{
            Token token = userService.registerToken(userSaved);
            return GeneralResponse.getResponse(HttpStatus.OK, "User registered successfully", token.getContent());
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }


//        return GeneralResponse.getResponse(HttpStatus.CREATED, "User registered successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody @Valid LoginUserDTO loginUserDTO){


        User _user = User.builder()
                .username(loginUserDTO.getIdentifier())
                .email(loginUserDTO.getIdentifier())
                .password(loginUserDTO.getPassword())
                .build();

        User userLogged = userService.signIn(_user);

        try{
            Token token = userService.registerToken(userLogged);
            return GeneralResponse.getResponse(HttpStatus.OK, "User logged in successfully", token.getContent());
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }


    }


//    DEBUG ONLY
    @GetMapping("/whoami")
    public ResponseEntity<GeneralResponse> whoAmI(@AuthenticationPrincipal User userDetails){

        User user = userService.findById(userDetails.getId());
        return GeneralResponse.getResponse(HttpStatus.OK, "User details [DEBUG ONLY]", user);

    }

    @GetMapping("/users")
    public ResponseEntity<GeneralResponse> getUsers(){
        return GeneralResponse.getResponse(HttpStatus.OK, "Users list", userService.findAll());
    }

    @GetMapping("/roles")
    public ResponseEntity<GeneralResponse> getRoles(){
        return GeneralResponse.getResponse(HttpStatus.OK, "Roles list", rolService.getAll());
    }



}
