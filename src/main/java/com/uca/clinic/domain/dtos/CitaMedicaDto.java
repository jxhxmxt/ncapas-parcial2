package com.uca.clinic.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CitaMedicaDto {
    private String motivo; // Candidata a desaparecer
    @NotBlank
    private String razon;
    @NotBlank
    private String fechaSolicitada;
}
