package com.example.vetproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Medicamento;
import com.example.vetproject.entity.Veterinario;

@Service
public interface VeterinarioService {
    public Veterinario SearchById(Long id);


    public List<Medicamento> getMedicamentos();

    public List<Veterinario> findAll();

    public void add(Veterinario veterinario);

    void deleteById(Long id);

    Veterinario update(Long id, Veterinario veterinario);
}
