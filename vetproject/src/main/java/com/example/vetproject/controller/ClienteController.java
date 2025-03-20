package com.example.vetproject.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Mascota;
import com.example.vetproject.error.NotFoundClientException;
import com.example.vetproject.error.NotFoundMascotException;
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

    @GetMapping("/find/{id}")
    public String InformacionCliente(Model model, @PathVariable("id") Long id) {
        Cliente cliente = clienteService.SearchById(id);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
        } else {
            throw new NotFoundClientException(id);
        }
        return "informacion_cliente.html";
    }


    @GetMapping("/all")
    public String MostrarClientes(Model model) {
        model.addAttribute("clientes", clienteService.SearchAll());
        return "clientes";
    }

    @GetMapping("/delete/{id}")
    public String EliminarCliente(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.SearchById(id);
        if (cliente == null) {
            throw new NotFoundClientException(id);
        }
        clienteService.deleteById(id);
        return "redirect:/clientes/all";
    }

    @GetMapping("/update/{id}")
    public String ActualizarCliente(Model model, @PathVariable("id") Long id) {
        Cliente cliente = clienteService.SearchById(id);
        if (id == null) {
            throw new NotFoundClientException(id);
        }
        model.addAttribute("cliente", cliente);
        return "cliente_update";
    }
    

    @PostMapping("/update/{id}")
    public String ActualizarCliente(@PathVariable("id") Long id, @Valid @ModelAttribute Cliente cliente, Model model) {
        Cliente clienteExistente = clienteService.SearchById(id);
        if (clienteExistente == null) {
            throw new NotFoundClientException(id);
        }
        
        cliente.setId(id);  // Establecemos explícitamente el ID
        cliente.setMascotas(clienteExistente.getMascotas());  // Mantenemos las mascotas existentes
        
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

    @GetMapping("/login")
public String MostrarLogin(Model model, @RequestParam(value = "error", required = false) String error) {
    model.addAttribute("cliente", new Cliente());
    
    if (error != null) {
        model.addAttribute("error", "Usuario no registrado o contraseña incorrecta.");
    }
    
    return "login.html";
}

    

@PostMapping("/login")
public String Login(@RequestParam("usuario") String usuario, @RequestParam("contrasena") String password, Model model) {
    Cliente cliente = clienteService.authenticate(usuario, password);
    if (cliente != null) {
        model.addAttribute("cliente", cliente);
        model.addAttribute("mascotas", cliente.getMascotas()); 
        return "mascotas_usuario.html";
    }

    model.addAttribute("error", "Usuario no registrado o contraseña incorrecta.");
    model.addAttribute("cliente", new Cliente()); // Aseguramos que 'cliente' esté en el modelo
    return "login.html";
}


}
