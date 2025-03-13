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
        "Juan", "Maria", "Carlos", "Ana", "Luis", "Elena", "Ricardo", "Sofia", "Fernando", "Gabriela",
        "Pedro", "Lucia", "Andres", "Camila", "Miguel", "Valentina", "David", "Isabela", "Jose", "Diana",
        "Daniel", "Paula", "Javier", "Sandra", "Hugo", "Raquel", "Emilio", "Veronica", "Manuel", "Laura",
        "Diego", "Carmen", "Alberto", "Beatriz", "Gustavo", "Irene", "Raul", "Silvia", "Francisco", "Patricia",
        "Sebastian", "Elisa", "Federico", "Noelia", "Alvaro", "Natalia", "Santiago", "Julia", "Victor", "Rosa"
    };

    private static final String[] APELLIDOS = {
        "Perez", "Gomez", "Rodriguez", "Lopez", "Martinez", "Quintero", "Fernandez", "Ramirez", "Castro", "Mendoza",
        "Ortega", "Herrera", "Jimenez", "Morales", "Santos", "Rubio", "Vargas", "Suarez", "Navarro", "Reyes"
    };

    private static final String[] TIPOS_MASCOTA = {"Perro", "Gato"};

    private static final String[] RAZAS_PERROS = {"Labrador", "Bulldog", "Beagle", "Pastor Aleman", "Golden Retriever"};
    private static final String[] RAZAS_GATOS = {"Siames", "Persa", "Maine Coon", "Bengali", "Siberiano"};

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

        clienteRepository.saveAll(clientes); 

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

        mascotaRepository.saveAll(mascotas); 
    }
}
