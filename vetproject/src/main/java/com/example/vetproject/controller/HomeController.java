package com.example.vetproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String inicio() {
        return "inicio.html";
    }

    @GetMapping("/sobre-nosotros")
    public String sobreNosotros() {
        return "sobre_nosotros.html";
    }

    @GetMapping("/servicios")
    public String servicios() {
        return "servicios.html";
    }

    @GetMapping("/reserva")
    public String reserva() {
        return "reserva_online.html";
    }
}
