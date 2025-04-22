package com.example.vetproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.repository.VeterinarioRepository;

@Service
public class VeterinarioServiceImplementation implements VeterinarioService{
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;
    
    @Override
    public Veterinario authenticate(String usuario, String contrasena) {
        Optional<Veterinario> veterinario = veterinarioRepository.findByUsuarioAndContrasena(usuario, contrasena);
        return veterinario.orElse(null);
    }
}
