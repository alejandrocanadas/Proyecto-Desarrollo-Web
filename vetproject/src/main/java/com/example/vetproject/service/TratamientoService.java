package com.example.vetproject.service;

import com.example.vetproject.entity.Tratamiento;
import java.util.List;

public interface TratamientoService {
    Tratamiento findById(Long id);
    List<Tratamiento> findAll();
    Tratamiento save(Tratamiento tratamiento);
    void deleteById(Long id);
} 
