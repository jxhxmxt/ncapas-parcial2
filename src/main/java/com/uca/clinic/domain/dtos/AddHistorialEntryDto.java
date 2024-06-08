package com.uca.clinic.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddHistorialEntryDto {
    @NotBlank
    private String identifier; // can be username or email

    @NotBlank
    private String reason;
}
