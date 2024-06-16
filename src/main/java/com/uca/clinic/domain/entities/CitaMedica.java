package com.uca.clinic.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.uca.clinic.utils.EstadoCita;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
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
    private User paciente;

    @OneToMany(mappedBy = "citaMedica", fetch = FetchType.EAGER)
    private List<Prescripcion> prescripciones = new ArrayList<>();

    @OneToMany(mappedBy = "citaMedica", fetch = FetchType.LAZY)
    private List<DetallesCitaMedica> detallesCitaMedica = new ArrayList<>();


}
