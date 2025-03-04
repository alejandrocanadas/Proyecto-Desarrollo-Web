package com.example.vetproject.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Cliente;
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
    public Collection<Cliente> SearchAll() {
        return clienteRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public void update(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void add(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    
}
