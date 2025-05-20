package com.example.vetproject.repository;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vetproject.entity.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> { 
    List<Mascota> findByClienteId(Long clienteId);
    long countByEstado(String estado);
    long count();

    @Query("SELECT m FROM Mascota m JOIN FETCH m.cliente c JOIN FETCH c.user WHERE m.id = :id")
    Optional<Mascota> findByIdWithClienteAndUser(@Param("id") Long id);
} 