package com.uca.clinic.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
