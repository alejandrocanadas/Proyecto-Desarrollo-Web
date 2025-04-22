package com.example.vetproject.service;

import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Veterinario;

@Service
public interface VeterinarioService {
    public Veterinario SearchById(Long id);

    public Veterinario authenticate(String username, String password);
}
