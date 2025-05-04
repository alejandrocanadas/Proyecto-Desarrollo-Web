package com.example.vetproject.controller;

import com.example.vetproject.DTO.TratamientoRequest;
import com.example.vetproject.entity.Tratamiento;
import com.example.vetproject.service.TratamientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TratamientosControllerMockTest {

    @Mock
    private TratamientoService tratamientoService;

    @InjectMocks
    private TratamientosController tratamientosController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTratamiento_Success() {
        // Arrange
        TratamientoRequest request = new TratamientoRequest();
        request.setMascotaId(1L);
        request.setVeterinarioId(1L);

        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre("Test Tratamiento");
        when(tratamientoService.createTratamiento(any(TratamientoRequest.class))).thenReturn(tratamiento);

        // Act
        ResponseEntity<?> response = tratamientosController.createTratamiento(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tratamiento, response.getBody());
        verify(tratamientoService, times(1)).createTratamiento(any(TratamientoRequest.class));
    }

    @Test
    public void testCreateTratamiento_Error() {
        // Arrange
        TratamientoRequest request = new TratamientoRequest();
        when(tratamientoService.createTratamiento(any(TratamientoRequest.class)))
            .thenThrow(new RuntimeException("Test error"));

        // Act
        ResponseEntity<?> response = tratamientosController.createTratamiento(request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Test error"));
        verify(tratamientoService, times(1)).createTratamiento(any(TratamientoRequest.class));
    }

    @Test
    public void testGetTratamientosByMascota() {
        // Arrange
        Tratamiento tratamiento1 = new Tratamiento();
        tratamiento1.setNombre("Tratamiento 1");
        Tratamiento tratamiento2 = new Tratamiento();
        tratamiento2.setNombre("Tratamiento 2");
        List<Tratamiento> tratamientos = Arrays.asList(tratamiento1, tratamiento2);
        when(tratamientoService.findByMascotaId(1L)).thenReturn(tratamientos);

        // Act
        List<Tratamiento> result = tratamientosController.getTratamientosByMascota(1L);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Tratamiento 1", result.get(0).getNombre());
        assertEquals("Tratamiento 2", result.get(1).getNombre());
        verify(tratamientoService, times(1)).findByMascotaId(1L);
    }

    @Test
    public void testGetTratamientosByVeterinario() {
        // Arrange
        Tratamiento tratamiento1 = new Tratamiento();
        tratamiento1.setNombre("Tratamiento 1");
        Tratamiento tratamiento2 = new Tratamiento();
        tratamiento2.setNombre("Tratamiento 2");
        List<Tratamiento> tratamientos = Arrays.asList(tratamiento1, tratamiento2);
        when(tratamientoService.findByVeterinarioId(1L)).thenReturn(tratamientos);

        // Act
        List<Tratamiento> result = tratamientosController.getTratamientosByVeterinario(1L);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Tratamiento 1", result.get(0).getNombre());
        assertEquals("Tratamiento 2", result.get(1).getNombre());
        verify(tratamientoService, times(1)).findByVeterinarioId(1L);
    }

    @Test
    public void testFindAllTratamientos() {
        // Arrange
        Tratamiento tratamiento1 = new Tratamiento();
        tratamiento1.setNombre("Tratamiento 1");
        Tratamiento tratamiento2 = new Tratamiento();
        tratamiento2.setNombre("Tratamiento 2");
        List<Tratamiento> tratamientos = Arrays.asList(tratamiento1, tratamiento2);
        when(tratamientoService.findAll()).thenReturn(tratamientos);

        // Act
        List<Tratamiento> result = tratamientoService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Tratamiento 1", result.get(0).getNombre());
        assertEquals("Tratamiento 2", result.get(1).getNombre());
        verify(tratamientoService, times(1)).findAll();
    }
} 