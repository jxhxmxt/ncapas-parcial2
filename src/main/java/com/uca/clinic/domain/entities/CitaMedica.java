package com.uca.clinic.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class CitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User paciente;

    @OneToMany(mappedBy = "citaMedica")
    private List<Prescripcion> prescripciones = new ArrayList<>();

    @OneToMany(mappedBy = "citaMedica")
    private List<DetallesCitaMedica> detallesCitaMedica = new ArrayList<>();

}
