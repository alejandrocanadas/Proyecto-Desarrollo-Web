package com.example.vetproject.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Mascota;

@Service
public interface ClienteService {
    public Cliente SearchById(Long id);

    public List<Cliente> SearchAll();

    public void deleteById(Long id);

    public void update(Cliente cliente);

    public void add(Cliente cliente);

    public Cliente findByEmail(String email);

    public int obtenerTotalClientes();

    Cliente findByUsername(String username);
}
