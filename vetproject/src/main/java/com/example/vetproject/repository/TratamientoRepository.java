package com.example.vetproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vetproject.entity.Tratamiento;
import java.util.Map;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long>{
    List<Tratamiento> findByMascotaId(Long mascotaId);
    List<Tratamiento> findByVeterinarioId(Long veterinarioId);
    long count();
    @Query("SELECT NEW MAP(p.nombre AS mascota, v.nombre AS veterinario, t.nombre AS medicamento, m.precioventa AS precio) " +
       "FROM Tratamiento t " +
       "JOIN t.mascota p " +
       "JOIN t.veterinario v " +
       "JOIN t.medicamentos m")
    List<Map<String, Object>> findTratamientosConMascotaVeterinarioYMedicamento();

    
} 
