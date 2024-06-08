package com.uca.clinic.services.impl;

import java.time.LocalDate;
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
  newCitaMedica.setFecha(LocalDate.now());
  newCitaMedica.setMotivo(citaMedica.getMotivo());
  newCitaMedica.setEstado("Pendiente");
  newCitaMedica.setPaciente(userDetails);
  
  return citaMedicaRepository.save(newCitaMedica);
 }

 @Override
 public void deleteById(Long id) {
  citaMedicaRepository.deleteById(id);
 }

 @Override
 public CitaMedica changeStatus(Long id, String newStatus) {
  CitaMedica citaMedica = citaMedicaRepository.findById(id).orElse(null);
  if(citaMedica == null) {
   return null;
  }
  citaMedica.setEstado(newStatus);
  return citaMedicaRepository.save(citaMedica);
 }

}