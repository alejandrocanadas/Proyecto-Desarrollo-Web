package com.example.vetproject.controller.Dashboards;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vetproject.service.ClienteService;
import com.example.vetproject.service.MascotaService;
import com.example.vetproject.service.TratamientoService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private TratamientoService tratamientoService;

    @GetMapping("/estadisticas")
    public ResponseEntity<Map<String, Integer>> obtenerEstadisticas() {
        Map<String, Integer> estadisticas = new HashMap<>();
        estadisticas.put("totalMascotas", mascotaService.obtenerTotalMascotas());
        estadisticas.put("totalClientes", clienteService.obtenerTotalClientes());
        return ResponseEntity.ok(estadisticas);
    }
    
}
