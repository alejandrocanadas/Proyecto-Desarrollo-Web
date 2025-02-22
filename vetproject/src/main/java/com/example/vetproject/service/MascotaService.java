package com.example.vetproject.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Mascota;

@Service
public interface MascotaService {

    public Mascota SearchById(int id);

    public Collection<Mascota> SeachAll();
    
} 
