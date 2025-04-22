package com.example.vetproject.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.vetproject.repository.TratamientoRepository;

public class TratamientoServiceImplementation implements TratamientoService {

    @Autowired
    TratamientoRepository tratamientoRepository;
    
    public int obtenerTotalTratamientos() {
        return (int) tratamientoRepository.count();
    }
}
