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
    public Mascota SearchById(Long id) {
        return mascotaRepository.findById(id).get();
    }

    @Override
    public Collection<Mascota> SeachAll() {
        return mascotaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    public void update(Mascota mascota) {
        mascotaRepository.save(mascota);
    }

    @Override
    public void add(Mascota mascota) {
        mascotaRepository.save(mascota);
    }
    
}
