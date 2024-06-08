package com.uca.clinic.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.uca.clinic.domain.dtos.CitaMedicaDto;
import com.uca.clinic.domain.entities.CitaMedica;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.repositories.CitaMedicaRepository;
import com.uca.clinic.services.CitaMedicaService;

@Service
@Component
public class CitaMedicaServiceImpl implements CitaMedicaService{

 private final CitaMedicaRepository citaMedicaRepository;

 public CitaMedicaServiceImpl(CitaMedicaRepository citaMedicaRepository) {
  this.citaMedicaRepository = citaMedicaRepository;
 }

 @Override
 public List<CitaMedica> findAll() {
  return citaMedicaRepository.findAll();
 }

 @Override
  public List<CitaMedica> findAllByEstado(CitaMedica.EstadoCita estado) {
    return citaMedicaRepository.findAllByEstado(estado);
  }

 @Override
 public CitaMedica findById(Long id) {
  return citaMedicaRepository.findById(id).orElse(null);
 }

 @Override
 public CitaMedica findByPacienteId(Long userId) {
  return citaMedicaRepository.findByPacienteId(userId);
 }

 @Override
 public CitaMedica save(User userDetails, CitaMedicaDto citaMedica) {
  CitaMedica newCitaMedica = new CitaMedica();
//  newCitaMedica.setFecha(Date.from(Instant.now()));
  newCitaMedica.setMotivo(citaMedica.getMotivo());
  newCitaMedica.setEstado(CitaMedica.EstadoCita.PENDIENTE_DE_APROBACION); // Fix: Pass the enum instance instead of a String
  newCitaMedica.setPaciente(userDetails);
  
  return citaMedicaRepository.save(newCitaMedica);
 }

 @Override
 public void deleteById(Long id) {
  citaMedicaRepository.deleteById(id);
 }

 @Override
 public CitaMedica changeStatus(Long id, CitaMedica.EstadoCita newStatus) {
  CitaMedica citaMedica = citaMedicaRepository.findById(id).orElse(null);
  if(citaMedica == null) {
   return null;
  }
  citaMedica.setEstado(newStatus);
  return citaMedicaRepository.save(citaMedica);
 }


}
