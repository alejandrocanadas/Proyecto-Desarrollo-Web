package com.example.vetproject.repository;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.example.vetproject.entity.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long>{
    List<Tratamiento> findByMascotaId(Long mascotaId);
    List<Tratamiento> findByVeterinarioId(Long veterinarioId);
    long count();
    int countByFechaAfter(LocalDateTime fecha);
    @Query("SELECT NEW MAP(p.nombre AS mascota, v.nombre AS veterinario, t.nombre AS medicamento, t.medicamento.precioventa AS precio) " +
       "FROM Tratamiento t " +
       "JOIN t.mascota p " +
       "JOIN t.veterinario v")
    List<Map<String, Object>> findTratamientosConMascotaVeterinarioYMedicamento();

    @Query("SELECT new map(t.nombre as medicamento, COUNT(t) as cantidad) " +
           "FROM Tratamiento t " +
           "WHERE t.fecha >= :fecha " +
           "GROUP BY t.nombre")
    List<Map<String, Object>> findTratamientosPorTipo(@Param("fecha") LocalDateTime fecha);

    @Query("SELECT new map(t.nombre as nombre, COUNT(t) as unidades) " +
           "FROM Tratamiento t " +
           "GROUP BY t.nombre " +
           "ORDER BY COUNT(t) DESC")
    List<Map<String, Object>> findTop3Tratamientos();

    @Query("SELECT COALESCE(SUM(t.medicamento.precioventa), 0) FROM Tratamiento t")
    double sumTotalVentas();

    @Query("SELECT COALESCE(SUM(t.medicamento.precioventa - t.medicamento.preciocompra), 0) FROM Tratamiento t")
    double sumGanancias();
} 
