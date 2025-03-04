package com.example.vetproject.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Mascota;

@Service
public interface ClienteService {
    public Cliente SearchById(Long id);

    public Collection<Cliente> SearchAll();

    public void deleteById(Long id);

    public void update(Cliente cliente);

    public void add(Cliente cliente);

}
