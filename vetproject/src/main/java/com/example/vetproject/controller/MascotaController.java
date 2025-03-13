package com.example.vetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Mascota;
import com.example.vetproject.error.NotFoundMascotException;
import com.example.vetproject.service.MascotaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    @GetMapping("/find/{id}")
    public String InformacionMascota(Model model, @PathVariable("id") Long id) {
        Mascota mascota = mascotaService.SearchById(id);
        if (mascota != null) {
            model.addAttribute("mascota", mascotaService.SearchById(id));
        } else {
            throw new NotFoundMascotException(id);
        }
        return "informacion_mascota.html";
    }

    @GetMapping("/all")
    public String MostrarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.SeachAll());
        return "mascotas.html";
    }

    @GetMapping("/delete/{id}")
    public String EliminarMascota(@PathVariable("id") Long id) {
        Mascota mascota = mascotaService.SearchById(id);
        if (mascota == null) {
            throw new NotFoundMascotException(id);
        }
        mascotaService.deleteById(id);
        return "redirect:/mascota/all";
    }

    @GetMapping("/update/{id}")
    public String ActualizarMascota(Model model, @PathVariable("id") Long id) {
        Mascota mascota = mascotaService.SearchById(id);
        if (mascota == null) {
            throw new NotFoundMascotException(id);
        }
        model.addAttribute("mascota", mascota);
        return "mascota_update.html";
    }

    @PostMapping("/update")
    public String ActualizarMascota(@ModelAttribute Mascota mascota) {
        Mascota anterior = mascotaService.SearchById(mascota.getId());
        mascota.setCliente(anterior.getCliente());

        mascotaService.update(mascota);
        return "redirect:/mascota/all";
    }

    @GetMapping("/add")
    public String AgregarMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "mascota_forms.html";
    }

    @PostMapping("/add")
    public String GuardarMascota(@ModelAttribute Mascota mascota) {
        System.out.println("Intentando guardar cliente...");
        mascotaService.add(mascota);
        return "redirect:/mascota/all";
    }
}
