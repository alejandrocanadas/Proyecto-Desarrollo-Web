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
    @Query("SELECT NEW MAP(m.nombre AS nombre, COUNT(t) AS cantidad) FROM Tratamiento t JOIN t.medicamentos m GROUP BY m.nombre")
    List<Map<String, Object>> findTratamientosPorMedicamento();

    
} 
