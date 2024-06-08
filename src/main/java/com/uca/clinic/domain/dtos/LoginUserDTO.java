package com.uca.clinic.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginUserDTO {
    @NotBlank
    private String identifier;
    @NotBlank
    private String password;
}
