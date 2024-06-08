package com.uca.clinic.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DetallesCitaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CitaMedica citaMedica;

    @ManyToOne
    private User medico;

    @ManyToOne
    private Especialidad especialidad;
}
