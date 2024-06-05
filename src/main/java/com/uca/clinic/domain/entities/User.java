package com.uca.clinic.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "roles")
    private Set<Rol> roles;

    @OneToMany(mappedBy = "user")
    private List<Historial> historiales;

    @OneToMany(mappedBy = "user")
    private Set<CitaMedica> citasMedicas;
}
