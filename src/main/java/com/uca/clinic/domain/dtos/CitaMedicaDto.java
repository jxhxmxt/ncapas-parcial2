package com.uca.clinic.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CitaMedicaDto {
    @NotEmpty
    private String motivo; // Candidata a desaparecer
    @NotEmpty
    private String razon;
    @NotEmpty
    private String fechaSolicitada;
}
