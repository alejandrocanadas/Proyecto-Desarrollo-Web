package com.example.vetproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vetproject.DTOs.TratamientoRequest;
import com.example.vetproject.entity.Mascota;
import com.example.vetproject.entity.Medicamento;
import com.example.vetproject.entity.Tratamiento;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.repository.MascotaRepository;
import com.example.vetproject.repository.MedicamentoRepository;
import com.example.vetproject.repository.TratamientoRepository;
import com.example.vetproject.repository.VeterinarioRepository;
import com.example.vetproject.service.TratamientoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/tratamientos")
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientosController {

    @Autowired
    private TratamientoService tratamientoService;

    @PostMapping
    @Operation(summary = "Crea un nuevo tratamiento")
    public ResponseEntity<?> createTratamiento(@RequestBody TratamientoRequest request) {
        try {
            Tratamiento tratamiento = tratamientoService.createTratamiento(request);
            return new ResponseEntity<>(tratamiento, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el tratamiento: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mascota/{mascotaId}")
    @Operation(summary = "Obtiene todos los tratamientos de una mascota")
    public ResponseEntity<?> getTratamientosByMascota(@PathVariable Long mascotaId) {
        try {
            List<Tratamiento> tratamientos = tratamientoService.findByMascotaId(mascotaId);
            if (tratamientos.isEmpty()) {
                return new ResponseEntity<>("No se encontraron tratamientos para la mascota con ID: " + mascotaId, 
                                          HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(tratamientos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener los tratamientos de la mascota: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/veterinario/{veterinarioId}")
    @Operation(summary = "Obtiene todos los tratamientos de un veterinario")
    public ResponseEntity<?> getTratamientosByVeterinario(@PathVariable Long veterinarioId) {
        try {
            List<Tratamiento> tratamientos = tratamientoService.findByVeterinarioId(veterinarioId);
            if (tratamientos.isEmpty()) {
                return new ResponseEntity<>("No se encontraron tratamientos para el veterinario con ID: " + veterinarioId, 
                                          HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(tratamientos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener los tratamientos del veterinario: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}