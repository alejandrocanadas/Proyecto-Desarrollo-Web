package com.example.vetproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.error.NotFoundClientException;
import com.example.vetproject.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:4200") // Angular por defecto
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/find/{id}")
    @Operation(summary = "Busca un cliente por id")
    public Cliente getCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.SearchById(id);
        if (cliente == null) {
            throw new NotFoundClientException(id);
        }
        return cliente;
    }

    @GetMapping("/all")
    @Operation(summary = "Busca todos los clientes")
    public List<Cliente> getAllClientes() {
        return clienteService.SearchAll();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina un cliente por id")
    public void deleteCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.SearchById(id);
        if (cliente == null) {
            throw new NotFoundClientException(id);
        }
        clienteService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualiza un cliente por id")
    public void updateCliente(@Valid @RequestBody Cliente cliente) {
        clienteService.update(cliente);
    }

    @PostMapping("/add")
    @Operation(summary = "Agrega un nuevo cliente")
    public void addCliente(@Valid @RequestBody Cliente cliente) {
        clienteService.add(cliente);
    }
    
    @PostMapping("/login")
    @Operation(summary = "Inicia sesion")
    public Cliente login(@RequestParam String usuario, @RequestParam String contrasena) {
        Cliente cliente = clienteService.authenticate(usuario, contrasena);
        if (cliente == null) {
            throw new RuntimeException("Usuario o contraseña incorrecta");
        }
        return cliente;
    }
}
