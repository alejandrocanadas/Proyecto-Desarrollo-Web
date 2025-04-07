package com.example.vetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Mascota obtenerMascota(@PathVariable("id") Long id) {
        Mascota mascota = mascotaService.SearchById(id);
        if (mascota == null) {
            throw new NotFoundMascotException(id);
        }
        return mascota;
    }

    @GetMapping("/all")
    @Operation(summary = "Obtiene todas las mascotas")
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaService.SeachAll();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina una mascota por su ID")
    public void eliminarMascota(@PathVariable("id") Long id) {
        Mascota mascota = mascotaService.SearchById(id);
        if (mascota == null) {
            throw new NotFoundMascotException(id);
        }
        mascotaService.deleteById(id);
    }

    @PostMapping("/update")
    @Operation(summary = "Actualiza una mascota")
    public void actualizarMascota(@RequestBody Mascota mascota) {
        Mascota anterior = mascotaService.SearchById(mascota.getId());
        if (anterior == null) {
            throw new NotFoundMascotException(mascota.getId());
        }
        mascota.setCliente(anterior.getCliente());
        mascotaService.update(mascota);
    }

    @PostMapping("/add")
    @Operation(summary = "Guarda una nueva mascota")
    public void guardarMascota(@RequestBody Mascota mascota, @RequestParam Long cliente_id) {
        Cliente cliente = clienteService.SearchById(cliente_id);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado con ID: " + cliente_id);
        }
        mascota.setCliente(cliente);
        mascotaService.add(mascota);
    }

    @GetMapping("/cliente/{id}")
    @Operation(summary = "Obtiene todas las mascotas de un cliente")
    public List<Mascota> obtenerMascotasPorCliente(@PathVariable("id") Long id) {
        return mascotaService.obtenerMascotasPorCliente(id);
    }
}
