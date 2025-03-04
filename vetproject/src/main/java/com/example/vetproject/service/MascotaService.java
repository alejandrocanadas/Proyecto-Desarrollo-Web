package com.example.vetproject.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Mascota;

@Service
public interface MascotaService {

    public Mascota SearchById(Long id);

    public Collection<Mascota> SeachAll();

    public void deleteById(Long id);

    public void update(Mascota mascota);

    public void add(Mascota mascota);
    
} 
