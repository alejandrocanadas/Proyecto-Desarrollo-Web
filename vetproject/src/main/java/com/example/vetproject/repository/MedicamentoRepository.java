package com.example.vetproject.repository;
import com.example.vetproject.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
