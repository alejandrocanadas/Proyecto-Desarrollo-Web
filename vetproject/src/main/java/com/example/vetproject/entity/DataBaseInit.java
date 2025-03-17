package com.example.vetproject.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import com.example.vetproject.repository.ClienteRepository;
import com.example.vetproject.repository.MascotaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
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
        "Perez", "Gomez", "Rodriguez", "Lopez", "Martinez", "Quintero", "Fernandez", "Ramirez", "Castro", "Mendoza"
    };

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
        List<Cliente> clientes = new ArrayList<>();

        // Lista manual de usuarios y correos
        String[][] datosClientes = {
            {"Juan", "Perez", "juanp", "juanp@example.com"},
            {"Maria", "Gomez", "mariag", "mariag@example.com"},
            {"Carlos", "Rodriguez", "carlosr", "carlosr@example.com"},
            {"Ana", "Lopez", "anal", "anal@example.com"},
            {"Luis", "Martinez", "luism", "luism@example.com"},
            {"Elena", "Quintero", "elenaq", "elenaq@example.com"},
            {"Ricardo", "Fernandez", "ricardof", "ricardof@example.com"},
            {"Sofia", "Ramirez", "sofiar", "sofiar@example.com"},
            {"Fernando", "Castro", "fernandoc", "fernandoc@example.com"},
            {"Gabriela", "Mendoza", "gabrielam", "gabrielam@example.com"},
            {"Pedro", "Ortega", "pedroo", "pedroo@example.com"},
            {"Lucia", "Herrera", "luciah", "luciah@example.com"},
            {"Andres", "Jimenez", "andresj", "andresj@example.com"},
            {"Camila", "Morales", "camilam", "camilam@example.com"},
            {"Miguel", "Santos", "miguels", "miguels@example.com"},
            {"Valentina", "Rubio", "valentinar", "valentinar@example.com"},
            {"David", "Vargas", "davidv", "davidv@example.com"},
            {"Isabela", "Suarez", "isabelas", "isabelas@example.com"},
            {"Jose", "Navarro", "josen", "josen@example.com"},
            {"Diana", "Reyes", "dianar", "dianar@example.com"},
            {"Daniel", "Perez", "danielp", "danielp@example.com"},
            {"Paula", "Gomez", "paulag", "paulag@example.com"},
            {"Javier", "Rodriguez", "javierr", "javierr@example.com"},
            {"Sandra", "Lopez", "sandral", "sandral@example.com"},
            {"Hugo", "Martinez", "hugom", "hugom@example.com"},
            {"Raquel", "Quintero", "raquelq", "raquelq@example.com"},
            {"Emilio", "Fernandez", "emiliof", "emiliof@example.com"},
            {"Veronica", "Ramirez", "veronicar", "veronicar@example.com"},
            {"Manuel", "Castro", "manuelc", "manuelc@example.com"},
            {"Laura", "Mendoza", "lauram", "lauram@example.com"},
            {"Diego", "Ortega", "diegog", "diegog@example.com"},
            {"Carmen", "Herrera", "carmenh", "carmenh@example.com"},
            {"Alberto", "Jimenez", "albertoj", "albertoj@example.com"},
            {"Beatriz", "Morales", "beatrizm", "beatrizm@example.com"},
            {"Gustavo", "Santos", "gustavos", "gustavos@example.com"},
            {"Irene", "Rubio", "irener", "irener@example.com"},
            {"Raul", "Vargas", "raulv", "raulv@example.com"},
            {"Silvia", "Suarez", "silvias", "silvias@example.com"},
            {"Francisco", "Navarro", "franciscon", "franciscon@example.com"},
            {"Patricia", "Reyes", "patriciar", "patriciar@example.com"},
            {"Sebastian", "Perez", "sebastianp", "sebastianp@example.com"},
            {"Elisa", "Gomez", "elisag", "elisag@example.com"},
            {"Federico", "Rodriguez", "federicor", "federicor@example.com"},
            {"Noelia", "Lopez", "noelial", "noelial@example.com"},
            {"Alvaro", "Martinez", "alvarom", "alvarom@example.com"},
            {"Natalia", "Quintero", "nataliaq", "nataliaq@example.com"},
            {"Santiago", "Fernandez", "santiagof", "santiagof@example.com"},
            {"Julia", "Ramirez", "juliar", "juliar@example.com"},
            {"Victor", "Castro", "victorc", "victorc@example.com"},
            {"Rosa", "Mendoza", "rosam", "rosam@example.com"}
        };

        // Crear usuarios manualmente
        for (String[] datos : datosClientes) {
            String nombre = datos[0];
            String apellido = datos[1];
            String usuario = datos[2];
            String email = datos[3];
            String telefono = "3" + (100000000 + (int) (Math.random() * 90000000));
            String contrasena = "password";

            Cliente cliente = new Cliente(nombre, usuario, apellido, telefono, email, contrasena);
            clientes.add(cliente);
        }

        clienteRepository.saveAll(clientes); // Guardar clientes en la BD

        // Crear y asignar mascotas (1 perro y 1 gato por usuario)
        List<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);

            Mascota perro = new Mascota("Firulais" + i, "Perro",
                    RAZAS_PERROS[i % RAZAS_PERROS.length], 2 + (i % 8),
                    IMAGENES[i % IMAGENES.length], cliente);

            Mascota gato = new Mascota("Mish" + i, "Gato",
                    RAZAS_GATOS[i % RAZAS_GATOS.length], 1 + (i % 10),
                    IMAGENES[(i + 1) % IMAGENES.length], cliente);

            mascotas.add(perro);
            mascotas.add(gato);
        }

        mascotaRepository.saveAll(mascotas);
        System.out.println("50 usuarios creados y 100 mascotas asignadas.");
    }
}
