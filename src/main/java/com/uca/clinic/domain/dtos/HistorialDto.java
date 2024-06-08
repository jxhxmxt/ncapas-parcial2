// NOTE!!!! THIS FILE IS NO LONGER BEING USED - LEFT HERE AS  PRE-EXAM LEGACY CODE
package com.uca.clinic.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
//@Builder
public class HistorialDto {
    private String details;
    private LocalDate date;
}
