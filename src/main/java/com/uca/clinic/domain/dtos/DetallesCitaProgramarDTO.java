package com.uca.clinic.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DetallesCitaProgramarDTO {

    private Long citaMedicaId;
    private Long medicoId;
    private String especialidadId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "America/El_Salvador")
    private Date fecha;

}
