package com.example.vetproject.service;

import com.example.vetproject.DTO.TratamientoRequest;
import com.example.vetproject.entity.Tratamiento;
import com.example.vetproject.entity.Mascota;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.entity.Medicamento;
import com.example.vetproject.repository.TratamientoRepository;
import com.example.vetproject.repository.MascotaRepository;
import com.example.vetproject.repository.VeterinarioRepository;
import com.example.vetproject.repository.MedicamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TratamientoServiceMockTest {

    @Mock
    private TratamientoRepository tratamientoRepository;

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private VeterinarioRepository veterinarioRepository;

    @Mock
    private MedicamentoRepository medicamentoRepository;

    @InjectMocks
    private TratamientoServiceImplementation tratamientoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        // Arrange
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setId(1L);
        tratamiento.setNombre("Test Tratamiento");
        when(tratamientoRepository.findById(1L)).thenReturn(Optional.of(tratamiento));

        // Act
        Tratamiento result = tratamientoService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Tratamiento", result.getNombre());
        verify(tratamientoRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        // Arrange
        Tratamiento tratamiento1 = new Tratamiento();
        tratamiento1.setNombre("Tratamiento 1");
        Tratamiento tratamiento2 = new Tratamiento();
        tratamiento2.setNombre("Tratamiento 2");
        List<Tratamiento> tratamientos = Arrays.asList(tratamiento1, tratamiento2);
        when(tratamientoRepository.findAll()).thenReturn(tratamientos);

        // Act
        List<Tratamiento> result = tratamientoService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(tratamientoRepository, times(1)).findAll();
    }

    @Test
    public void testCreateTratamiento() {
        // Arrange
        TratamientoRequest request = new TratamientoRequest();
        request.setMascotaId(1L);
        request.setVeterinarioId(1L);
        request.setMedicamentoId(1L);

        Mascota mascota = new Mascota();
        mascota.setId(1L);
        Veterinario veterinario = new Veterinario();
        veterinario.setId(1L);
        Medicamento medicamento = new Medicamento();
        medicamento.setId(1L);
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre("Test Tratamiento");

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));
        when(veterinarioRepository.findById(1L)).thenReturn(Optional.of(veterinario));
        when(medicamentoRepository.findById(1L)).thenReturn(Optional.of(medicamento));
        when(tratamientoRepository.save(any(Tratamiento.class))).thenReturn(tratamiento);

        // Act
        Tratamiento result = tratamientoService.createTratamiento(request);

        // Assert
        assertNotNull(result);
        assertEquals("Test Tratamiento", result.getNombre());
        verify(tratamientoRepository, times(1)).save(any(Tratamiento.class));
    }

    @Test
    public void testDeleteById() {
        // Act
        tratamientoService.deleteById(1L);

        // Assert
        verify(tratamientoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByMascotaId() {
        // Arrange
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre("Test Tratamiento");
        List<Tratamiento> tratamientos = Arrays.asList(tratamiento);
        when(tratamientoRepository.findByMascotaId(1L)).thenReturn(tratamientos);

        // Act
        List<Tratamiento> result = tratamientoService.findByMascotaId(1L);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Tratamiento", result.get(0).getNombre());
        verify(tratamientoRepository, times(1)).findByMascotaId(1L);
    }

    @Test
    public void testFindByVeterinarioId() {
        // Arrange
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre("Test Tratamiento");
        List<Tratamiento> tratamientos = Arrays.asList(tratamiento);
        when(tratamientoRepository.findByVeterinarioId(1L)).thenReturn(tratamientos);

        // Act
        List<Tratamiento> result = tratamientoService.findByVeterinarioId(1L);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Tratamiento", result.get(0).getNombre());
        verify(tratamientoRepository, times(1)).findByVeterinarioId(1L);
    }
} 