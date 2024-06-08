package com.uca.clinic.domain.dtos;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
public class CitaMedicaDto {
    private String motivo;
}
