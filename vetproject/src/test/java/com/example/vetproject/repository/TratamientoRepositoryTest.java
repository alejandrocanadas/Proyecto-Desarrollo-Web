package com.example.vetproject.repository;

import com.example.vetproject.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class TratamientoRepositoryTest {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Mascota mascota;
    private Veterinario veterinario;
    private Medicamento medicamento;
    private Tratamiento tratamiento;

    @BeforeEach
    void setUp() {
        Cliente cliente = new Cliente("Carlos", "123456", "Lopez", "999999999", "carlos@example.com", "password");
        cliente = clienteRepository.save(cliente);

        mascota = new Mascota("Fido", "Perro", "Labrador", "Macho", "Saludable", 5, null, cliente);
        mascota = mascotaRepository.save(mascota);

        veterinario = new Veterinario("Dra. Ana", "321654987", "ana@example.com", "anaVet", "clave123", "Activo");
        veterinario = veterinarioRepository.save(veterinario);

        medicamento = new Medicamento("Vacuna Triple", 1000.0, 2000.0, 10, 0);
        medicamento = medicamentoRepository.save(medicamento);

        tratamiento = new Tratamiento("Vacunacion anual", veterinario, mascota, List.of(medicamento));
        tratamiento = tratamientoRepository.save(tratamiento);
    }

    @Test
    void testFindByMascotaId() {
        List<Tratamiento> resultados = tratamientoRepository.findByMascotaId(mascota.getId());
        assertThat(resultados).hasSize(1);
        assertThat(resultados.get(0).getMascota().getNombre()).isEqualTo("Fido");
    }

    @Test
    void testFindByVeterinarioId() {
        List<Tratamiento> resultados = tratamientoRepository.findByVeterinarioId(veterinario.getId());
        assertThat(resultados).hasSize(1);
        assertThat(resultados.get(0).getVeterinario().getNombre()).isEqualTo("Dra. Ana");
    }

    @Test
    void testCountTratamientos() {
        long conteo = tratamientoRepository.count();
        assertThat(conteo).isGreaterThan(0);
    }

    @Test
    void testFindById() {
        Tratamiento encontrado = tratamientoRepository.findById(tratamiento.getId()).orElse(null);
        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getNombre()).isEqualTo("Vacunacion anual");
    }
}
