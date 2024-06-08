package com.uca.clinic.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class CitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha; // Candidata a desaparecer
    private Date fechaRealizacion;
    private Date fechaFinalizacion;
    private Date fechaSolicitada;
    private Date fechaEstimadaFinalizacion;
    private String motivo;
    private String razon;
    private EstadoCita estado;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User paciente;

    @OneToMany(mappedBy = "citaMedica", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Prescripcion> prescripciones = new ArrayList<>();

    @OneToMany(mappedBy = "citaMedica", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DetallesCitaMedica> detallesCitaMedica = new ArrayList<>();

    public enum EstadoCita {
        PENDIENTE_DE_APROBACION,
        PENDIENTE_DE_EJECUCION,
        EN_EJECUCION,
        FINALIZADA,
        RECHAZADA,
        CANCELADA
    }
}
