package com.uca.clinic.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Prescripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicamento;
    private String dosis;
    private String instrucciones;

    @ManyToOne
    @JoinColumn(name = "cita_medica_id")
    private CitaMedica citaMedica;
}
