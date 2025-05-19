package com.example.vetproject.service;

import com.example.vetproject.entity.Tratamiento;
import com.example.vetproject.DTOs.TratamientoRequest;
import java.util.List;

public interface TratamientoService {
    Tratamiento findById(Long id);
    List<Tratamiento> findAll();
    Tratamiento save(Tratamiento tratamiento);
    void deleteById(Long id);
    Tratamiento createTratamiento(TratamientoRequest request);
    List<Tratamiento> findByMascotaId(Long mascotaId);
    List<Tratamiento> findByVeterinarioId(Long veterinarioId);
}