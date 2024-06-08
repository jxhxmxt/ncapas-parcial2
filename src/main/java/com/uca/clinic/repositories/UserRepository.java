package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByEmailOrUsername(String identifier, String identifier1);

    Boolean existsByEmailOrUsername(String email, String username);

    boolean existsByUsername(String admin);


//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u LEFT JOIN u.detallesCitaMedicaMedico dc WHERE u.id = :id AND dc.citaMedica.fecha BETWEEN :date1 AND :date2")
//    boolean checkIsAviable(Long id,Date date1, Date date2);
//
//
//    @Query("SELECT u FROM User u LEFT JOIN u.detallesCitaMedicaMedico dc WHERE u.id = :id AND dc.citaMedica.fecha BETWEEN :date1 AND :date2")
//    List<User> findAllByDetallesCitaMedicaMedicoCitaMedicaFechaBetween(Long id, Date date1, Date date2);
//
//    @Query("SELECT u FROM User u LEFT JOIN u.detallesCitaMedicaMedico dc WHERE u.id = :id")
//    List<User> findAllByDetallesCitaMedicaMedicoCitaMedicaFechaBetween(Long id);


}
