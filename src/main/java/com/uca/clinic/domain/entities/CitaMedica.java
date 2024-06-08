package com.uca.clinic.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class CitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String motivo;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User paciente;

    @OneToMany(mappedBy = "citaMedica", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Prescripcion> prescripciones = new ArrayList<>();

    @OneToMany(mappedBy = "citaMedica", fetch = FetchType.LAZY)
    private List<DetallesCitaMedica> detallesCitaMedica = new ArrayList<>();

}
