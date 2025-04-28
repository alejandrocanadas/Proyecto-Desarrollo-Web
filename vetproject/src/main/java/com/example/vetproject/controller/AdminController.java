package com.example.vetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vetproject.entity.Admin;
import com.example.vetproject.entity.LoginRequest;
import com.example.vetproject.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    @Operation(summary = "Inicia sesión como administrador")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Admin admin = adminService.authenticate(loginRequest.getUsuario(), loginRequest.getContrasena());
        if (admin == null) {
            return new ResponseEntity<>("Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
}
