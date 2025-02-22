package com.example.vetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vetproject.entity.Mascota;
import com.example.vetproject.service.MascotaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/mascota")
public class MascotaController {
    
    @Autowired
    MascotaService mascotaService;

    @GetMapping("/find/{id}")
    public String InformacionMascota(Model model, @PathVariable("id") int id) {
         model.addAttribute("mascota", mascotaService.SearchById(id));
         return "informacion_mascota.html";
    }

    @GetMapping("/all")
    public String MostrarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.SeachAll());
        return "mascotas.html";
    }
    
}
