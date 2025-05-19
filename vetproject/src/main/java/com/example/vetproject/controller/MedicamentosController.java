package com.example.vetproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vetproject.entity.Medicamento;
import com.example.vetproject.repository.MedicamentoRepository;
import com.example.vetproject.service.MedicamentoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentosController {
    
    @Autowired
    private MedicamentoService medicamentoService;
    
    @GetMapping
    @Operation(summary = "Obtiene todos los medicamentos")
    public ResponseEntity<List<Medicamento>> getAllMedicamentos() {
        try {
            List<Medicamento> medicamentos = medicamentoService.findAll();
            return new ResponseEntity<>(medicamentos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un medicamento por su ID")
    public ResponseEntity<?> getMedicamentoById(@PathVariable Long id) {
        try {
            Medicamento medicamento = medicamentoService.findById(id);
            if (medicamento == null) {
                return new ResponseEntity<>("Medicamento no encontrado con ID: " + id, 
                                          HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(medicamento, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al buscar el medicamento: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}/stock")
    @Operation(summary = "Actualiza el stock de un medicamento")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestParam int cantidad) {
        try {
            Medicamento medicamento = medicamentoService.updateStock(id, cantidad);
            if (medicamento == null) {
                return new ResponseEntity<>("Medicamento no encontrado con ID: " + id, 
                                          HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(medicamento, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el stock: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}