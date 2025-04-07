package com.example.vetproject.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Mascota;

@Service
public interface MascotaService {

    public Mascota SearchById(Long id);

    public List<Mascota> SeachAll();

    public void deleteById(Long id);

    public void update(Mascota mascota);

    public void add(Mascota mascota);
    
    public List<Mascota> obtenerMascotasPorCliente(Long clienteId);
} 
