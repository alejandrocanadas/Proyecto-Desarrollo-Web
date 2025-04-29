package com.example.vetproject.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vetproject.entity.Admin;
import com.example.vetproject.entity.LoginRequest;
import com.example.vetproject.service.AdminService;
import com.example.vetproject.repository.TratamientoRepository;
import com.example.vetproject.repository.VeterinarioRepository;
import com.example.vetproject.repository.MascotaRepository;
import com.example.vetproject.repository.ClienteRepository;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/login")
    @Operation(summary = "Inicia sesión como administrador")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Admin admin = adminService.authenticate(loginRequest.getUsuario(), loginRequest.getContrasena());
        if (admin == null) {
            return new ResponseEntity<>("Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @GetMapping("/estadisticas")
    @Operation(summary = "Obtiene estadísticas para el panel de administración")
    public ResponseEntity<Map<String, Object>> getAdminDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        List<Map<String, Object>> tratamientos = tratamientoRepository.findTratamientosConMascotaVeterinarioYMedicamento();
        System.out.println(tratamientos);
        // Número total de tratamientos administrados
        long totalTratamientos = tratamientoRepository.count();
        // Número de veterinarios activos e inactivos
        long veterinariosActivos = veterinarioRepository.count();
        long clientesActivos = clienteRepository.count();
        // Número de mascotas activas e inactivas
        long mascotasActivas = mascotaRepository.countByEstado("Activo");
        long mascotasInactivas = mascotaRepository.countByEstado("Inactivo");

        stats.put("totalTratamientos", totalTratamientos);
        stats.put("veterinariosActivos", veterinariosActivos);
        stats.put("mascotasActivas", mascotasActivas);
        stats.put("mascotasInactivas", mascotasInactivas);
        stats.put("clientesActivos", clientesActivos);
        stats.put("tratamientos", tratamientos);
        
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

}
