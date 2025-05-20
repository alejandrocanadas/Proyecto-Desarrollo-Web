package com.example.vetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Mascota;
import com.example.vetproject.error.NotFoundMascotException;
import com.example.vetproject.service.MascotaService;

import io.swagger.v3.oas.annotations.Operation;

import com.example.vetproject.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    @Autowired
    ClienteService clienteService;

    @GetMapping("/find/{id}")
    @Operation(summary = "Obtiene una mascota por su ID")
    public ResponseEntity<?> obtenerMascota(@PathVariable("id") Long id, Authentication authentication) {
        try {
            Mascota mascota = mascotaService.SearchById(id);
            if (mascota == null) {
                throw new NotFoundMascotException(id);
            }
            // Si es CLIENTE, solo puede ver su propia mascota
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CLIENTE"))) {
                String username = authentication.getName();
                if (!mascota.getCliente().getUser().getUsername().equals(username)) {
                    return new ResponseEntity<>("No autorizado", HttpStatus.FORBIDDEN);
                }
            }
            return new ResponseEntity<>(mascota, HttpStatus.OK);
        } catch (NotFoundMascotException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Obtiene todas las mascotas")
    public ResponseEntity<List<Mascota>> obtenerTodasLasMascotas() {
        List<Mascota> mascotas = mascotaService.SeachAll();
        return new ResponseEntity<>(mascotas, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina una mascota por su ID")
    public ResponseEntity<?> eliminarMascota(@PathVariable("id") Long id) {
        try {
            Mascota mascota = mascotaService.SearchById(id);
            if (mascota == null) {
                throw new NotFoundMascotException(id);
            }
            mascotaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundMascotException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update")
    @Operation(summary = "Actualiza una mascota")
    public ResponseEntity<?> actualizarMascota(@RequestBody Mascota mascota) {
        try {
            Mascota anterior = mascotaService.SearchById(mascota.getId());
            if (anterior == null) {
                throw new NotFoundMascotException(mascota.getId());
            }
            mascota.setCliente(anterior.getCliente());
            mascotaService.update(mascota);
            return new ResponseEntity<>(mascota, HttpStatus.OK);
        } catch (NotFoundMascotException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la mascota: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Guarda una nueva mascota")
    public ResponseEntity<?> guardarMascota(@RequestBody Mascota mascota, @RequestParam Long cliente_id) {
        try {
            Cliente cliente = clienteService.SearchById(cliente_id);
            if (cliente == null) {
                return new ResponseEntity<>("Cliente no encontrado con ID: " + cliente_id, 
                                          HttpStatus.NOT_FOUND);
            }
            mascota.setCliente(cliente);
            mascotaService.add(mascota);
            return new ResponseEntity<>(mascota, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la mascota: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cliente/{id}")
    @Operation(summary = "Obtiene todas las mascotas de un cliente")
    public ResponseEntity<?> obtenerMascotasPorCliente(@PathVariable("id") Long id) {
        try {
            List<Mascota> mascotas = mascotaService.obtenerMascotasPorCliente(id);
            return new ResponseEntity<>(mascotas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener las mascotas del cliente: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
