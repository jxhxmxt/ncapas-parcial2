package com.uca.clinic.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles;

    @OneToMany(mappedBy = "user")
    private List<Historial> historiales;

    @OneToMany(mappedBy = "paciente")
    private List<CitaMedica> citasMedicasPaciente;

    @OneToMany(mappedBy = "medico")
    private List<DetallesCitaMedica> detallesCitaMedicaMedico;
}
