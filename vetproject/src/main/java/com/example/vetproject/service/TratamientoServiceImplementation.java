package com.example.vetproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.vetproject.entity.Tratamiento;
import com.example.vetproject.entity.Mascota;
import com.example.vetproject.entity.Medicamento;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.DTO.TratamientoRequest;
import com.example.vetproject.repository.TratamientoRepository;
import com.example.vetproject.repository.MascotaRepository;
import com.example.vetproject.repository.VeterinarioRepository;
import com.example.vetproject.repository.MedicamentoRepository;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class TratamientoServiceImplementation implements TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;
    
    @Autowired
    private MascotaRepository mascotaRepository;
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;
    
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public Tratamiento findById(Long id) {
        return tratamientoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tratamiento> findAll() {
        return tratamientoRepository.findAll();
    }

    @Override
    public Tratamiento save(Tratamiento tratamiento) {
        return tratamientoRepository.save(tratamiento);
    }

    @Override
    public void deleteById(Long id) {
        tratamientoRepository.deleteById(id);
    }
    
    @Override
    public Tratamiento createTratamiento(TratamientoRequest request) {
        Mascota mascota = mascotaRepository.findById(request.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
                
        Veterinario veterinario = veterinarioRepository.findById(request.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
                
        Medicamento medicamento = medicamentoRepository.findById(request.getMedicamentoId())
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
                
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre(medicamento.getNombre());
        tratamiento.setMascota(mascota);
        tratamiento.setVeterinario(veterinario);
        tratamiento.setMedicamento(medicamento);
        tratamiento.setFecha(LocalDateTime.now());
        
        // Primero guardamos el tratamiento
        tratamiento = save(tratamiento);
        
        // Luego actualizamos el medicamento
        medicamento.setStock(medicamento.getStock() - 1);
        medicamento.setUvendidas(medicamento.getUvendidas() + 1);
        medicamentoRepository.save(medicamento);
        
        return tratamiento;
    }
    
    @Override
    public List<Tratamiento> findByMascotaId(Long mascotaId) {
        return tratamientoRepository.findByMascotaId(mascotaId);
    }
    
    @Override
    public List<Tratamiento> findByVeterinarioId(Long veterinarioId) {
        return tratamientoRepository.findByVeterinarioId(veterinarioId);
    }
}