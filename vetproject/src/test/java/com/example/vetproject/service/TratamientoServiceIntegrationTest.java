package com.example.vetproject.service;

import com.example.vetproject.DTO.TratamientoRequest;
import com.example.vetproject.entity.Tratamiento;
import com.example.vetproject.entity.Mascota;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Medicamento;
import com.example.vetproject.repository.TratamientoRepository;
import com.example.vetproject.repository.MascotaRepository;
import com.example.vetproject.repository.VeterinarioRepository;
import com.example.vetproject.repository.ClienteRepository;
import com.example.vetproject.repository.MedicamentoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TratamientoServiceImplementation.class)
@ActiveProfiles("test")
public class TratamientoServiceIntegrationTest {

    @Autowired
    private TratamientoService tratamientoService;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Test
    public void testCreateAndFindTratamiento() {
        // Create test data
        Cliente cliente = new Cliente("Daniel", "Daniel123", "Garcia", "123456789", "vYU6t@example.com", "password");
        cliente = clienteRepository.save(cliente);
        assertNotNull(cliente.getId(), "El ID del cliente no debe ser null");

        Mascota mascota = new Mascota("Mailo", "Perro", "Labrador", "Macho", "Activo", 5, "https://example.com/mailo.jpg", cliente);
        mascota = mascotaRepository.save(mascota);
        assertNotNull(mascota.getId(), "El ID de la mascota no debe ser null");

        Veterinario veterinario = new Veterinario();
        veterinario.setNombre("Test Veterinario");
        veterinario.setEmail("testvet@example.com");
        veterinario.setTelefono("3100000000");
        veterinario.setUsuario("testvet");
        veterinario.setContrasena("vet123");
        veterinario.setEstado("Activo");
        veterinario = veterinarioRepository.save(veterinario);
        assertNotNull(veterinario.getId(), "El ID del veterinario no debe ser null");

        Medicamento medicamento = new Medicamento("Test Medicamento", 100.0, 150.0, 10, 0);
        medicamento = medicamentoRepository.save(medicamento);
        assertNotNull(medicamento.getId(), "El ID del medicamento no debe ser null");

        // Create tratamiento request
        TratamientoRequest request = new TratamientoRequest();
        request.setMascotaId(mascota.getId());
        request.setVeterinarioId(veterinario.getId());
        request.setMedicamentoId(medicamento.getId());

        // Create tratamiento
        Tratamiento tratamiento = tratamientoService.createTratamiento(request);
        assertNotNull(tratamiento, "El tratamiento creado no debe ser null");
        assertNotNull(tratamiento.getId(), "El ID del tratamiento creado no debe ser null");
        assertEquals(medicamento.getNombre(), tratamiento.getNombre());

        // Find by ID
        Tratamiento found = tratamientoService.findById(tratamiento.getId());
        assertNotNull(found);
        assertEquals(tratamiento.getId(), found.getId());

        // Find by mascota
        List<Tratamiento> tratamientosMascota = tratamientoService.findByMascotaId(mascota.getId());
        assertFalse(tratamientosMascota.isEmpty());
        assertEquals(tratamiento.getId(), tratamientosMascota.get(0).getId());

        // Find by veterinario
        List<Tratamiento> tratamientosVeterinario = tratamientoService.findByVeterinarioId(veterinario.getId());
        assertFalse(tratamientosVeterinario.isEmpty());
        assertEquals(tratamiento.getId(), tratamientosVeterinario.get(0).getId());
    }

    @Test
    public void testDeleteTratamiento() {
        // Create test data
        Cliente cliente = new Cliente("Daniel", "Daniel123", "Garcia", "123456789", "vYU6t@example.com", "password");
        cliente = clienteRepository.save(cliente);
        assertNotNull(cliente.getId(), "El ID del cliente no debe ser null");

        Mascota mascota = new Mascota("Mailo", "Perro", "Labrador", "Macho", "Activo", 5, "https://example.com/mailo.jpg", cliente);
        mascota = mascotaRepository.save(mascota);
        assertNotNull(mascota.getId(), "El ID de la mascota no debe ser null");

        Veterinario veterinario = new Veterinario();
        veterinario.setNombre("Test Veterinario");
        veterinario.setEmail("testvet@example.com");
        veterinario.setTelefono("3100000000");
        veterinario.setUsuario("testvet");
        veterinario.setContrasena("vet123");
        veterinario.setEstado("Activo");
        veterinario = veterinarioRepository.save(veterinario);
        assertNotNull(veterinario.getId(), "El ID del veterinario no debe ser null");

        Medicamento medicamento = new Medicamento("Test Medicamento", 100.0, 150.0, 10, 0);
        medicamento = medicamentoRepository.save(medicamento);
        assertNotNull(medicamento.getId(), "El ID del medicamento no debe ser null");

        // Create tratamiento
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setMascota(mascota);
        tratamiento.setVeterinario(veterinario);
        tratamiento.setNombre(medicamento.getNombre());
        tratamiento.getMedicamentos().add(medicamento);
        tratamiento = tratamientoRepository.save(tratamiento);
        assertNotNull(tratamiento.getId(), "El ID del tratamiento no debe ser null");

        // Delete tratamiento
        tratamientoService.deleteById(tratamiento.getId());

        // Verify deletion
        assertNull(tratamientoService.findById(tratamiento.getId()));
    }
} 