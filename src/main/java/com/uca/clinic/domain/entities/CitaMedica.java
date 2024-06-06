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

    @ManyToMany
    @JoinTable(
            name = "cita_medica_medico",
            joinColumns = @JoinColumn(name = "cita_medica_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> medicos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User paciente;

    @OneToMany(mappedBy = "citaMedica")
    private List<Prescripcion> prescripciones = new ArrayList<>();
}
