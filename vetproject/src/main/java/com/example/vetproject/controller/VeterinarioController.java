package com.example.vetproject.controller;

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

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.LoginRequest;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.error.NotFoundClientException;
import com.example.vetproject.service.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;

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

    @PostMapping("/login")
    @Operation(summary = "Inicia sesion")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Veterinario veterinario = veterinarioService.authenticate(loginRequest.getUsuario(), loginRequest.getContrasena());
        if (veterinario == null) {
            return new ResponseEntity<>("Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(veterinario, HttpStatus.OK);
    }
}
