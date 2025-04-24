package com.example.vetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.LoginRequest;
import com.example.vetproject.entity.Tratamiento;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.error.NotFoundClientException;
import com.example.vetproject.service.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

@RestController
@RequestMapping("/veterinario")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {
    @Autowired
    VeterinarioService veterinarioService;

    @GetMapping("/find/{id}")
    @Operation(summary = "Busca un veterinario por id")
    public Veterinario getCliente(@PathVariable Long id) {
        Veterinario veterinario = veterinarioService.SearchById(id);
        if (veterinario == null) {
            throw new NotFoundClientException(id);
        }
        return veterinario;
    }

    @GetMapping("tratamientos/{id}")
    @Operation(summary = "Obtiene todos los tratamientos asignados a un veterinario")
    public ResponseEntity<List<Tratamiento>> getTratamientosVeterinario(@PathVariable Long id) {
        Veterinario veterinario = veterinarioService.SearchById(id);
        if (veterinario == null) {
            throw new NotFoundClientException(id);
        }
        List<Tratamiento> tratamientos = veterinario.getTratamientos();
        return new ResponseEntity<>(tratamientos, HttpStatus.OK);
    }

    @PostMapping("/login")
    @Operation(summary = "Inicia sesion")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Veterinario veterinario = veterinarioService.authenticate(loginRequest.getUsuario(), loginRequest.getContrasena());
        if (veterinario == null) {
            return new ResponseEntity<>("Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(veterinario, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los veterinarios")
    public ResponseEntity<List<Veterinario>> getAllVeterinarios() {
        List<Veterinario> veterinarios = veterinarioService.findAll();
        return new ResponseEntity<>(veterinarios, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo veterinario")
    public void addVeterinario(@RequestBody Veterinario veterinario) {
        veterinarioService.add(veterinario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un veterinario existente")
    public ResponseEntity<?> updateVeterinario(@PathVariable Long id, 
                                             @RequestBody Veterinario veterinario) {
        try {
            Veterinario updatedVeterinario = veterinarioService.update(id, veterinario);
            if (updatedVeterinario != null) {
                return new ResponseEntity<>(updatedVeterinario, HttpStatus.OK);
            }
            return new ResponseEntity<>("Veterinario no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el veterinario: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un veterinario")
    public ResponseEntity<?> deleteVeterinario(@PathVariable Long id) {
        try {
            veterinarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el veterinario: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
