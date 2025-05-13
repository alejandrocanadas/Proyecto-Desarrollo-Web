package com.example.vetproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDateTime;

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
        LocalDateTime fecha = LocalDateTime.now();
        // Tratamientos del último mes
        stats.put("totalTratamientosUltimoMes", tratamientoRepository.countByFechaAfter(LocalDateTime.now().minusMonths(1)));
        
        // Tratamientos por tipo de medicamento
        List<Map<String, Object>> tratamientosPorTipo = tratamientoRepository.findTratamientosPorTipo(fecha);
        stats.put("tratamientosPorTipo", tratamientosPorTipo);

        // Estado de veterinarios
        stats.put("veterinariosActivos", veterinarioRepository.countByEstado("Activo"));
        stats.put("veterinariosInactivos", veterinarioRepository.countByEstado("Inactivo"));

        // Estado de mascotas
        stats.put("totalMascotas", mascotaRepository.count());
        stats.put("mascotasActivas", mascotaRepository.countByEstado("Activo"));

        // Ventas y ganancias (calculadas desde tratamientos)
        stats.put("ventasTotales", tratamientoRepository.sumTotalVentas());
        stats.put("gananciasTotales", tratamientoRepository.sumGanancias());

        // Top 3 tratamientos
        List<Map<String, Object>> top3Tratamientos = tratamientoRepository.findTop3Tratamientos();
        stats.put("top3Tratamientos", top3Tratamientos);

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
