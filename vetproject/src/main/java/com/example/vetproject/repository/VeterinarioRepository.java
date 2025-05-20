package com.example.vetproject.repository;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Veterinario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    
    Veterinario findByEmail(String email);
    Veterinario findByTelefono(String telefono);
    Optional<Veterinario> findByUserUsername(String username);
    long count();
    int countByEstado(String estado);
}
