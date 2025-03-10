package com.example.vetproject.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import com.example.vetproject.repository.ClienteRepository;
import com.example.vetproject.repository.MascotaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

@Controller
@Transactional
public class DataBaseInit implements ApplicationRunner {

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    private static final String[] NOMBRES_CLIENTES = {
        "Juan", "María", "Carlos", "Ana", "Luis", "Elena", "Ricardo", "Sofía", "Fernando", "Gabriela",
        "Pedro", "Lucía", "Andrés", "Camila", "Miguel", "Valentina", "David", "Isabela", "José", "Diana",
        "Daniel", "Paula", "Javier", "Sandra", "Hugo", "Raquel", "Emilio", "Verónica", "Manuel", "Laura",
        "Diego", "Carmen", "Alberto", "Beatriz", "Gustavo", "Irene", "Raúl", "Silvia", "Francisco", "Patricia",
        "Sebastián", "Elisa", "Federico", "Noelia", "Álvaro", "Natalia", "Santiago", "Julia", "Víctor", "Rosa"
    };

    private static final String[] APELLIDOS = {
        "Pérez", "Gómez", "Rodríguez", "López", "Martínez", "Quintero", "Fernández", "Ramírez", "Castro", "Mendoza",
        "Ortega", "Herrera", "Jiménez", "Morales", "Santos", "Rubio", "Vargas", "Suárez", "Navarro", "Reyes"
    };

    private static final String[] TIPOS_MASCOTA = {"Perro", "Gato"};

    private static final String[] RAZAS_PERROS = {"Labrador", "Bulldog", "Beagle", "Pastor Alemán", "Golden Retriever"};
    private static final String[] RAZAS_GATOS = {"Siamés", "Persa", "Maine Coon", "Bengalí", "Siberiano"};

    private static final String[] IMAGENES = {
        "https://www.costaartabra.es/wp-content/uploads/2021/08/leo1-web.jpg",
        "https://mp.reshift.nl/zoom/ce270323-a8dc-4381-a730-888ad9c1d1ec-labrador-pup.jpeg?w=460",
        "https://www.zooplus.es/magazine/wp-content/uploads/2019/06/franz%C3%B6sische-bulldogge-1024x683.jpg",
        "https://www.zooplus.es/magazine/wp-content/uploads/2019/03/deutscher-sch%C3%A4ferhund.jpg",
        "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/Siberiano.jpg"
    };

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Random rand = new Random();
        List<Cliente> clientes = new ArrayList<>();

        // Crear 50 clientes
        for (int i = 0; i < 50; i++) {
            String nombre = NOMBRES_CLIENTES[rand.nextInt(NOMBRES_CLIENTES.length)];
            String apellido = APELLIDOS[rand.nextInt(APELLIDOS.length)];
            String usuario = nombre.toLowerCase() + apellido.toLowerCase();
            String telefono = "3" + (rand.nextInt(100000000) + 100000000);
            String email = usuario + "@example.com";
            String contrasena = "password";

            Cliente cliente = new Cliente(nombre, usuario, apellido, telefono, email, contrasena);
            clientes.add(cliente);
        }

        clienteRepository.saveAll(clientes); // Guardar clientes en la BD

        // Crear 100 mascotas (2 por cliente)
        List<Mascota> mascotas = new ArrayList<>();
        for (Cliente cliente : clientes) {
            for (int j = 0; j < 2; j++) {
                String tipo = TIPOS_MASCOTA[rand.nextInt(TIPOS_MASCOTA.length)];
                String raza = tipo.equals("Perro") ? RAZAS_PERROS[rand.nextInt(RAZAS_PERROS.length)]
                        : RAZAS_GATOS[rand.nextInt(RAZAS_GATOS.length)];
                String nombreMascota = tipo.equals("Perro") ? "Firulais" + j : "Mish" + j;
                int edad = rand.nextInt(10) + 1;
                String imagenUrl = IMAGENES[rand.nextInt(IMAGENES.length)];

                Mascota mascota = new Mascota(nombreMascota, tipo, raza, edad, imagenUrl, cliente);
                cliente.addMascota(mascota);
                mascotas.add(mascota);
            }
        }

        mascotaRepository.saveAll(mascotas); // Guardar mascotas en la BD
    }
}
