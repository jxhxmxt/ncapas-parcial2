package com.uca.clinic.domain.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PrescripcionDTO {
    @NotBlank
    private String medicamento;
    @NotBlank
    private String dosis;

}
