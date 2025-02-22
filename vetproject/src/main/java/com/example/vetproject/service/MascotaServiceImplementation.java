package com.example.vetproject.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Mascota;
import com.example.vetproject.repository.MascotaRepository;

@Service
public class MascotaServiceImplementation implements MascotaService{
    @Autowired
    MascotaRepository mascotaRepository;    

    @Override
    public Mascota SearchById(int id) {
        return mascotaRepository.findById(id);
    }

    @Override
    public Collection<Mascota> SeachAll() {
        return mascotaRepository.findAll();
    }
    
}
