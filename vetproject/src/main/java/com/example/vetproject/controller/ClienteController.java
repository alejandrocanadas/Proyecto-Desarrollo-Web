package com.example.vetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("find/{id}")
    public String InformacionCliente(Model model, @PathVariable("id") Long id) {
        model.addAttribute("cliente", clienteService.SearchById(id));
        return "informacion_cliente";
    }

    @GetMapping("/all")
    public String MostrarClientes(Model model) {
        model.addAttribute("clientes", clienteService.SearchAll());
        return "clientes";
    }

    @GetMapping("/delete/{id}")
    public String EliminarCliente(@PathVariable("id") Long id) {
        clienteService.deleteById(id);
        return "redirect:/clientes/all";
    }

    @GetMapping("/update/{id}")
    public String ActualizarCliente(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cliente", clienteService.SearchById(id));
        return "cliente_forms.html";
    }

    @PostMapping("/update/{id}")
    public String ActualizarCliente(@ModelAttribute Cliente cliente) {
        clienteService.update(cliente);
        return "redirect:/clientes/all";
    }

    @GetMapping("/add")
    public String AgregarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente_forms.html";
    }

    @PostMapping("/add")
    public String GuardarCliente(@ModelAttribute Cliente cliente) {
        System.out.println("Intentando guardar cliente...");
        clienteService.add(cliente); 
        return "redirect:/clientes/all";
    }
}
