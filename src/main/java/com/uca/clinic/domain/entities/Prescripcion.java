package com.uca.clinic.domain.entities;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
public class Prescripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicamento;
    private String dosis;

    @ManyToOne
    @JoinColumn(name = "cita_medica_id")
    private CitaMedica citaMedica;

    public Prescripcion() {
    }

    public Prescripcion(Long id, String medicamento, String dosis, CitaMedica citaMedica) {
        this.id = id;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.citaMedica = citaMedica;
    }
}
