package com.example.vetproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.vetproject.entity.Medicamento;
import com.example.vetproject.repository.MedicamentoRepository;
import java.util.List;

@Service
public class MedicamentoServiceImplementation implements MedicamentoService {
    
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    
    @Override
    public List<Medicamento> findAll() {
        return medicamentoRepository.findAll();
    }
    
    @Override
    public Medicamento findById(Long id) {
        return medicamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
    }
    
    @Override
    public Medicamento save(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }
    
    @Override
    public void deleteById(Long id) {
        medicamentoRepository.deleteById(id);
    }
    
    @Override
    public Medicamento updateStock(Long id, int cantidad) {
        Medicamento medicamento = findById(id);
        medicamento.setStock(medicamento.getSotck() - cantidad);
        medicamento.setUvendidas(medicamento.getUvendidas() + cantidad);
        return save(medicamento);
    }
}