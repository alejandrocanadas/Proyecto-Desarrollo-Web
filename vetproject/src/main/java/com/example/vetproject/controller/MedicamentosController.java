package com.example.vetproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vetproject.entity.Medicamento;
import com.example.vetproject.repository.MedicamentoRepository;
import com.example.vetproject.service.MedicamentoService;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentosController {
    
    @Autowired
    private MedicamentoService medicamentoService;
    
    @GetMapping
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoService.findAll();
    }
    
    @GetMapping("/{id}")
    public Medicamento getMedicamentoById(@PathVariable Long id) {
        return medicamentoService.findById(id);
    }
    
    @PutMapping("/{id}/stock")
    public Medicamento updateStock(@PathVariable Long id, @RequestParam int cantidad) {
        return medicamentoService.updateStock(id, cantidad);
    }
}