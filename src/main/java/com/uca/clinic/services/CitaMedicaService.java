package com.uca.clinic.services;

import java.util.List;

import com.uca.clinic.domain.dtos.CitaMedicaDto;
import com.uca.clinic.domain.entities.CitaMedica;

public interface CitaMedicaService {
	List<CitaMedica> findAll();
	CitaMedica findById(Long id);
	CitaMedica findByUserId(Long userId);
	CitaMedica save(CitaMedicaDto citaMedica);
	void deleteById(Long id);
	String changeStatus(Long id, String newStatus);
}
