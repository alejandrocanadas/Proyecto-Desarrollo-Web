package com.example.vetproject.service;

import java.util.Collection;
import java.util.List;

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
        if (mascota.getId() == null || !mascotaRepository.existsById(mascota.getId())) {
            throw new IllegalArgumentException("La mascota con ID " + mascota.getId() + " no existe y no puede ser actualizada.");
        }
        mascotaRepository.save(mascota);
    }

    @Override
    public void add(Mascota mascota) {
        mascotaRepository.save(mascota);
    }

    @Override
    public List<Mascota> obtenerMascotasPorCliente(Long clienteId) {
        return mascotaRepository.findByClienteId(clienteId);
    }
    
}
