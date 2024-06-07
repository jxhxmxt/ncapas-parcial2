package com.uca.clinic.domain.entities.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginUserDTO {
    @NotBlank
    private String identifier;
    @NotBlank
    private String password;
}
