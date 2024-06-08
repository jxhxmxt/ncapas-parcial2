package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
    List<Historial> findByUserId(Long userId);
    List<Historial> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, Date startDate, Date endDate);
    List<Historial> findByUserIdAndDateAfterOrderByDateDesc(Long userId, Date startDate);
    List<Historial> findByUserIdAndDateBeforeOrderByDateDesc(Long userId, Date endDate);
    List<Historial> findByUserIdOrderByDateDesc(Long userId);
}
