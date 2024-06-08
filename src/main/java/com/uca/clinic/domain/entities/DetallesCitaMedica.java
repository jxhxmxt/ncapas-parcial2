package com.uca.clinic.domain.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class DetallesCitaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"detallesCitaMedica"})
    private CitaMedica citaMedica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"detallesCitaMedicaMedico"})
    private User medico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Especialidad especialidad;
}
