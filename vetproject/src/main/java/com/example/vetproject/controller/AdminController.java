package com.example.vetproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.vetproject.entity.UserEntity;
import com.example.vetproject.DTOs.AdminDTO;
import com.example.vetproject.DTOs.AdminMapper;
import com.example.vetproject.entity.Admin;
import com.example.vetproject.entity.LoginRequest;
import com.example.vetproject.service.AdminService;
import com.example.vetproject.repository.TratamientoRepository;
import com.example.vetproject.repository.VeterinarioRepository;
import com.example.vetproject.repository.MascotaRepository;
import com.example.vetproject.repository.ClienteRepository;
import com.example.vetproject.security.CustomUserDetailService;
import com.example.vetproject.security.JWTGenerator;

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

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    @PostMapping("/login")
    @Operation(summary = "Inicia sesión como administrador")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsuario(), 
                    loginRequest.getContrasena()
                )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGenerator.generateToken(authentication);

            return new ResponseEntity<String>(token, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/estadisticas")
    @Operation(summary = "Obtiene estadísticas para el panel de administración")
    public ResponseEntity<Map<String, Object>> getAdminDashboardStats() {
        try {
            Map<String, Object> stats = new HashMap<>();
            LocalDateTime fecha = LocalDateTime.now().minusMonths(1);
            // Tratamientos del último mes
            stats.put("totalTratamientosUltimoMes",
                    tratamientoRepository.countByFechaAfter(LocalDateTime.now().minusMonths(1)));

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
            stats.put("totalTratamientos", tratamientoRepository.count());

            // Top 3 tratamientos
            List<Map<String, Object>> top3Tratamientos = tratamientoRepository.findTop3Tratamientos();
            stats.put("top3Tratamientos", top3Tratamientos);

            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Agrega un nuevo administrador")
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin) {
        try {
            String rawPassword = "admin123";
            UserEntity userEntity = customUserDetailService.adminToUser(admin, rawPassword);
            admin.setUser(userEntity);
            adminService.add(admin);
            AdminDTO adminDTO = AdminMapper.INSTANCE.convert(admin);
            return new ResponseEntity<>(adminDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el administrador: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
