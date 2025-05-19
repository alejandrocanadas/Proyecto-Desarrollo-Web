package com.example.vetproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Medicamento;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.repository.MedicamentoRepository;
import com.example.vetproject.repository.VeterinarioRepository;

@Service
public class VeterinarioServiceImplementation implements VeterinarioService{

    @Autowired
    private MedicamentoRepository medicamentoRepository;
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Override
    public Veterinario SearchById(Long id) {
        return veterinarioRepository.findById(id).get();
    }
    
    

    @Override
    public List<Veterinario> findAll() {
        return veterinarioRepository.findAll();
    }

    @Override
    public void add(Veterinario veterinario) {
        veterinarioRepository.save(veterinario);
    }

    @Override
    public void deleteById(Long id) {
        veterinarioRepository.deleteById(id);
    }

    @Override
    public Veterinario update(Long id, Veterinario veterinario) {
        Veterinario existingVeterinario = SearchById(id);
        if (existingVeterinario != null) {
            veterinario.setId(id);
            return veterinarioRepository.save(veterinario);
        }
        return null;
    }

    @Override
    public List<Medicamento> getMedicamentos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMedicamentos'");
    }

}
