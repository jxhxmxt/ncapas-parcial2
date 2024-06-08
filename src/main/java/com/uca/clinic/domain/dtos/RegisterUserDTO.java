package com.uca.clinic.domain.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserDTO {
    @NotBlank
    @Email
    String email;

    @NotBlank
    String password;

    @NotBlank
    String nombre;

    @NotBlank
    String username;
}
