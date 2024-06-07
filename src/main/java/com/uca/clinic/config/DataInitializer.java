package com.uca.clinic.config;


import com.uca.clinic.domain.entities.Rol;
import com.uca.clinic.repositories.RolRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataInitializer {


    @Bean
    @Transactional
    public CommandLineRunner initDatabase(RolRepository rolRepository) {
        return args -> {
            initRoles(rolRepository);
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
}
