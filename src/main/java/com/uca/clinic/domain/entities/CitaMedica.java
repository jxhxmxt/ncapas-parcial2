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
            name = "cita_user",
            joinColumns = @JoinColumn(name = "cita_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> usuarios = new HashSet<>();

    @OneToMany(mappedBy = "citaMedica")
    private List<Prescripcion> prescripciones = new ArrayList<>();
}
