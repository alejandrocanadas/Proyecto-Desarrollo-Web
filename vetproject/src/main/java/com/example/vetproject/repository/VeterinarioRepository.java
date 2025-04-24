package com.example.vetproject.repository;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Veterinario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Optional<Veterinario> findByUsuarioAndContrasena(String usuario, String contrasena);
    Veterinario findByEmail(String email);
    Veterinario findByTelefono(String telefono);
    Veterinario findByUsuario(String usuario);
}
