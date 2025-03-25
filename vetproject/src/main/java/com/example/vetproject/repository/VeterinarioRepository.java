package com.example.vetproject.repository;

import com.example.vetproject.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
