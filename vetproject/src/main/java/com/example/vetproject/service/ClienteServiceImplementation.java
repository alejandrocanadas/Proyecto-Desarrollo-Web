package com.example.vetproject.service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Mascota;
import com.example.vetproject.repository.ClienteRepository;

@Service
public class ClienteServiceImplementation implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Cliente SearchById(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public List<Cliente> SearchAll() {
        return clienteRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public void update(Cliente cliente) {
        if (cliente.getId() == null || !clienteRepository.existsById(cliente.getId())) {
            throw new IllegalArgumentException("el cliente con ID " + cliente.getId() + " no existe y no puede ser actualizada.");
        }
        clienteRepository.save(cliente);
    }

    @Override
    public void add(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    
    
    @Override
    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public int obtenerTotalClientes() {
        return (int) clienteRepository.count();
    }

    @Override
    public Cliente findByUsername(String username) {
        return clienteRepository.findByUserUsername(username).orElse(null);
    }
}
