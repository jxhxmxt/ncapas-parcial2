package com.uca.clinic.controllers;


import com.uca.clinic.domain.entities.Token;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.domain.entities.dto.LoginUserDTO;
import com.uca.clinic.domain.entities.dto.RegisterUserDTO;
import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.services.UserService;
import com.uca.clinic.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
   private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
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




}
