package com.example.vetproject.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.vetproject.entity.Cliente;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    @BeforeEach
    void setup() {
        clienteRepository.deleteAll();
        Cliente cliente1 = new Cliente("Lolita", "Lolita123", "Garcia", "123456789", "vYU6t@example.com", "password");
        Cliente cliente2 = new Cliente("Pepito", "Pepito123", "Roman", "987654321", "vYfds6t@example.com", "password");

        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);
    }

    @Test
    void clienteRepository_save_Cliente() {
        Cliente cliente = new Cliente("Juan", "Juan123", "Perez", "555555555", "juan@example.com", "password");
        Cliente clienteGuardado = clienteRepository.save(cliente);

        Assertions.assertThat(clienteGuardado).isNotNull();
        Assertions.assertThat(clienteGuardado.getNombre()).isEqualTo("Juan");
    }

    @Test
    void ClienteRepository_findAll_NotEmptyList() {
        List<Cliente> clientes = clienteRepository.findAll();
        Assertions.assertThat(clientes).isNotEmpty();
        Assertions.assertThat(clientes.size()).isEqualTo(2);
    }

    @Test
    void ClienteRepository_findById_Cliente() {
        Long id = clienteRepository.findAll().get(0).getId();
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        Assertions.assertThat(cliente).isNotNull();
        Assertions.assertThat(cliente.getNombre()).isEqualTo("Lolita");
    }

    @Test
    void ClienteRepository_delete_Cliente() {
        Long id = clienteRepository.findAll().get(0).getId();
        clienteRepository.deleteById(id);

        Assertions.assertThat(clienteRepository.findById(id)).isEmpty();
    }

    @Test
    void ClienteRepository_update_Cliente() {
        Long id = clienteRepository.findAll().get(0).getId();
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        cliente.setNombre("Lolita Actualizado");

        Cliente clienteActualizado = clienteRepository.save(cliente);

        Assertions.assertThat(clienteActualizado).isNotNull();
        Assertions.assertThat(clienteActualizado.getNombre()).isEqualTo("Lolita Actualizado");
    }

    @Test
    void ClienteRepository_findByUsuarioAndContrasena() {
        Optional<Cliente> cliente = clienteRepository.findByUsuarioAndContrasena("Lolita123", "password");

        Assertions.assertThat(cliente).isPresent();
        Assertions.assertThat(cliente.get().getNombre()).isEqualTo("Lolita");
    }

    @Test
    void ClienteRepository_findByEmail() {
        Cliente cliente = clienteRepository.findByEmail("vYU6t@example.com");

        Assertions.assertThat(cliente).isNotNull();
        Assertions.assertThat(cliente.getNombre()).isEqualTo("Lolita");
    }

    @Test
    void ClienteRepository_count_Clientes() {
        long total = clienteRepository.count();

        Assertions.assertThat(total).isEqualTo(2); 
    }

}
