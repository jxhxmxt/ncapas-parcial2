package com.uca.clinic.services;

import java.util.Date;
import java.util.List;

import com.uca.clinic.domain.dtos.CitaMedicaDto;
import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.DetallesCitaMedica;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.utils.EstadoCita;

public interface CitaMedicaService {
	List<CitaMedica> findAll();

	List<CitaMedica> findAllByEstado(EstadoCita estado);

	CitaMedica findById(Long id);

	CitaMedica findByPacienteId(Long userId);

	CitaMedica save(User userDetails, CitaMedicaDto citaMedica);

	void deleteById(Long id);

	CitaMedica changeStatus(Long id, EstadoCita newStatus);

	List<CitaMedica> findByUserAndEstado(User user, EstadoCita estado);

	List<CitaMedica> findByUser(User user);

	List<CitaMedica> findByUserAndDate(User user, Date date);

	List<CitaMedica> findByDoctorAndDate(List<DetallesCitaMedica> detallesCitaMedica, Date date);
}
