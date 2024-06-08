package com.uca.clinic.domain.dtos;

import lombok.Data;

@Data
public class DetallesCitaProgramarDTO {

    private Long citaMedicaId;
    private Long medicoId;
    private String especialidadId;
    private String fecha;

}
