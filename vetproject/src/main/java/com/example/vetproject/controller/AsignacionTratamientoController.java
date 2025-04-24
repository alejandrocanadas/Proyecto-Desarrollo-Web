package com.example.vetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vetproject.dto.AsignacionTratamientoDTO;
import com.example.vetproject.entity.Mascota;
import com.example.vetproject.entity.Tratamiento;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.service.MascotaService;
import com.example.vetproject.service.TratamientoService;
import com.example.vetproject.service.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/asignacion-tratamiento")
@CrossOrigin(origins = "http://localhost:4200")
public class AsignacionTratamientoController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private TratamientoService tratamientoService;

    @Autowired
    private VeterinarioService veterinarioService;

    @PostMapping("/asignar")
    @Operation(summary = "Asigna un tratamiento a una mascota")
    public ResponseEntity<?> asignarTratamiento(@RequestBody AsignacionTratamientoDTO asignacion) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Obtener las entidades
            Mascota mascota = mascotaService.SearchById(asignacion.getMascotaId());
            Tratamiento tratamiento = tratamientoService.findById(asignacion.getTratamientoId());
            Veterinario veterinario = veterinarioService.SearchById(asignacion.getVeterinarioId());

            if (mascota == null || tratamiento == null || veterinario == null) {
                response.put("error", "No se encontró la mascota, el tratamiento o el veterinario");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Asignar el tratamiento a la mascota
            mascota.addTratamiento(tratamiento);
            tratamiento.setMascota(mascota);
            tratamiento.setVeterinario(veterinario);

            // Guardar los cambios
            mascotaService.save(mascota);
            tratamientoService.save(tratamiento);

            response.put("mensaje", "Tratamiento asignado correctamente");
            response.put("mascota", mascota);
            response.put("tratamiento", tratamiento);
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", "Error al asignar el tratamiento: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 