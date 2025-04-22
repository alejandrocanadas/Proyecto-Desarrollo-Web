package com.example.vetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.LoginRequest;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.service.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/veterinario")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {
    @Autowired
    VeterinarioService veterinarioService;

    @PostMapping("/login")
    @Operation(summary = "Inicia sesion")
    public Veterinario login(@RequestBody LoginRequest loginRequest) {
        Veterinario veterinario = veterinarioService.authenticate(loginRequest.getUsuario(), loginRequest.getContrasena());
        if (veterinario == null) {
            throw new RuntimeException("Usuario o contraseña incorrecta");
        }
        return veterinario;
    }
}
