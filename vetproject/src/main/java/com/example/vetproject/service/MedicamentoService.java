package com.example.vetproject.service;

import com.example.vetproject.entity.Medicamento;
import java.util.List;

public interface MedicamentoService {
    List<Medicamento> findAll();
    Medicamento findById(Long id);
    Medicamento save(Medicamento medicamento);
    void deleteById(Long id);
    Medicamento updateStock(Long id, int cantidad);
}