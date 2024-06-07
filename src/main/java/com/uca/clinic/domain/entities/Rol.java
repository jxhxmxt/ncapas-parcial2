package com.uca.clinic.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Rol {
    @Id
    private String id;
    private String nombre;

    @ManyToMany(mappedBy = "roles")
    private Set<User> usuarios;
}
