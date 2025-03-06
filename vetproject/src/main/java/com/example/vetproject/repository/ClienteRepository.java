package com.example.vetproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vetproject.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Optional<Cliente> findByUsuarioAndContrasena(String usuario, String contrasena);
    
}
