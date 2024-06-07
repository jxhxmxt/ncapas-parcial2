package com.uca.clinic.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String username;
    private String email;
    @JsonIgnore
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Token> tokens;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }



}
