package com.uca.clinic.config;


import com.uca.clinic.domain.entities.Especialidad;
import com.uca.clinic.domain.entities.Rol;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.repositories.EspecialidadRepository;
import com.uca.clinic.repositories.RolRepository;
import com.uca.clinic.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;


@Configuration
public class DataInitializer {


    @Bean
    @Transactional
    public CommandLineRunner initDatabase(RolRepository rolRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, EspecialidadRepository especialidadRepository){
        return args -> {
            initRoles(rolRepository);
            initAdmin(userRepository, rolRepository, passwordEncoder);
            initEspecialidades(especialidadRepository);
            initMedics(userRepository, rolRepository, passwordEncoder);
        };
    }


    @Transactional
    public void initRoles(RolRepository rolRepository){
        String[] rolesNames = { "ADMIN", "PACIENTE", "MEDICO", "ASISTENTE" };
        for (String roleName : rolesNames) {
            if (!rolRepository.existsByNombre(roleName)) {
                Rol rol = new Rol();
                rol.setId(roleName.substring(0,4).toUpperCase());
                rol.setNombre(roleName);
                rolRepository.save(rol);
            }
        }
    }

    @Transactional
    public void initAdmin(UserRepository userRepository, RolRepository rolRepository, BCryptPasswordEncoder passwordEncoder){
        if (!userRepository.existsByUsername("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setEmail("admin@admin.com");
            user.setNombre("admin");
            user.setPassword(passwordEncoder.encode("admin"));
//            user.setRoles(Set.of(rolRepository.findByNombre("ADMIN")));
            user.setRoles(Set.of(
                    rolRepository.findByNombre("ADMIN"),
                    rolRepository.findByNombre("PACIENTE"),
                    rolRepository.findByNombre("MEDICO"),
                    rolRepository.findByNombre("ASISTENTE")
            ));
            userRepository.save(user);
        }
    }

    @Transactional
    public void initMedics(UserRepository userRepository, RolRepository rolRepository, BCryptPasswordEncoder passwordEncoder){
        if (!userRepository.existsByUsername("medico")) {
            User user = new User();
            user.setUsername("medico");
            user.setEmail("medicTest@dameil1.com");
            user.setNombre("medico");
            user.setPassword(passwordEncoder.encode("medico"));
            user.setRoles(Set.of(
                    rolRepository.findByNombre("MEDICO")
            ));
            userRepository.save(user);
        }

        if (!userRepository.existsByUsername("medico2")) {
            User user = new User();
            user.setUsername("medico2");
            user.setEmail("medic2Test@dameil1.com");
            user.setNombre("medico2");
            user.setPassword(passwordEncoder.encode("medico2"));
            user.setRoles(Set.of(
                    rolRepository.findByNombre("MEDICO")
            ));
            userRepository.save(user);
        }
    }


    @Transactional
    public void initEspecialidades(EspecialidadRepository especialidadRepository){
        String[] especialidades = { "Cardiología", "Dermatología", "Endocrinología", "Gastroenterología", "Geriatría", "Ginecología", "Hematología", "Infectología", "Medicina interna", "Nefrología", "Neumología", "Neurología", "Nutriología", "Oftalmología", "Oncología", "Pediatría", "Psiquiatría", "Reumatología", "Traumatología", "Urología" };
        for (String especialidad : especialidades) {
            if (!especialidadRepository.existsByNombre(especialidad)) {
                Especialidad _especialidad = new Especialidad();
                _especialidad.setId(especialidad.substring(0,4).toUpperCase());
                _especialidad.setNombre(especialidad);
                especialidadRepository.save(_especialidad);
            }
        }
    }
}
