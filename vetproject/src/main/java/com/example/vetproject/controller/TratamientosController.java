package com.example.vetproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vetproject.DTO.TratamientoRequest;
import com.example.vetproject.entity.Mascota;
import com.example.vetproject.entity.Medicamento;
import com.example.vetproject.entity.Tratamiento;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.repository.MascotaRepository;
import com.example.vetproject.repository.MedicamentoRepository;
import com.example.vetproject.repository.TratamientoRepository;
import com.example.vetproject.repository.VeterinarioRepository;
import com.example.vetproject.service.TratamientoService;

@RestController
@RequestMapping("/api/tratamientos")
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientosController {

    @Autowired
    private TratamientoService tratamientoService;

    @PostMapping
    public ResponseEntity<?> createTratamiento(@RequestBody TratamientoRequest request) {
        try {
            Tratamiento tratamiento = tratamientoService.createTratamiento(request);
            return ResponseEntity.ok(tratamiento);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear el tratamiento: " + e.getMessage());
        }
    }

    @GetMapping("/mascota/{mascotaId}")
    public List<Tratamiento> getTratamientosByMascota(@PathVariable Long mascotaId) {
        return tratamientoService.findByMascotaId(mascotaId);
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public List<Tratamiento> getTratamientosByVeterinario(@PathVariable Long veterinarioId) {
        return tratamientoService.findByVeterinarioId(veterinarioId);
    }
}