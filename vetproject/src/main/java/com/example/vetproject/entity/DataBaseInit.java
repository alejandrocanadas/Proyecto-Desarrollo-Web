package com.example.vetproject.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import com.example.vetproject.repository.ClienteRepository;
import com.example.vetproject.repository.MascotaRepository;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public class DataBaseInit implements ApplicationRunner {

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Cliente juan = new Cliente("Juan", "juan123", "Pérez", "3001234567", "juan.perez@example.com", "password123");
        Cliente maria = new Cliente("María", "maria99", "Gómez", "3107654321", "maria.gomez@example.com", "mariaPass99");
        Cliente carlos = new Cliente("Carlos", "carlosx", "Rodríguez", "3209876543", "carlos.rod@example.com", "carlitos2023");
        Cliente ana = new Cliente("Ana", "anita20", "López", "3015678901", "ana.lopez@example.com", "anaSuperPwd");
        Cliente luis = new Cliente("Luis", "luisito", "Martínez", "3154321098", "luis.mtz@example.com", "luisPass321");
        Cliente elena = new Cliente("Elena", "elenaQ", "Quintero", "3056789123", "elena.quintero@example.com", "elenaSegura");
        Cliente ricardo = new Cliente("Ricardo", "ricko", "Fernández", "3123456789", "ricardo.fernandez@example.com", "rickPass123");
        Cliente sofia = new Cliente("Sofía", "sofycool", "Ramírez", "3221098765", "sofia.ram@example.com", "sof123456");
        Cliente fernando = new Cliente("Fernando", "fercho", "Castro", "3045678912", "fernando.castro@example.com", "ferPassword");
        Cliente gabriela = new Cliente("Gabriela", "gabi99", "Mendoza", "3112233445", "gabriela.mendoza@example.com", "gabiPass123");
        
        clienteRepository.save(juan);
        clienteRepository.save(maria);
        clienteRepository.save(carlos);
        clienteRepository.save(ana);
        clienteRepository.save(luis);
        clienteRepository.save(elena);
        clienteRepository.save(ricardo);
        clienteRepository.save(sofia);
        clienteRepository.save(fernando);
        clienteRepository.save(gabriela);
        
        mascotaRepository.save(new Mascota("Luna", "Perro", "Golden Retriever", 3, "https://www.costaartabra.es/wordpress/wp-content/uploads/2021/08/leo1-web.jpg", juan));
        mascotaRepository.save(new Mascota("Max", "Perro", "Labrador", 4, "https://mp.reshift.nl/zoom/ce270323-a8dc-4381-a730-888ad9c1d1ec-labrador-pup.jpeg?w=460", maria));
        mascotaRepository.save(new Mascota("Bella", "Perro", "Bulldog Francés", 2, "https://www.zooplus.es/magazine/wp-content/uploads/2019/06/franz%C3%B6sische-bulldogge-1024x683.jpg", carlos));
        mascotaRepository.save(new Mascota("Rocky", "Perro", "Pastor Alemán", 5, "https://www.zooplus.es/magazine/wp-content/uploads/2019/03/deutscher-sch%C3%A4ferhund.jpg", ana));
        mascotaRepository.save(new Mascota("Toby", "Perro", "Beagle", 1, "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/Beagle-1.jpg", luis));
        mascotaRepository.save(new Mascota("Milo", "Gato", "Persa", 3, "https://cdn.wamiz.fr/cdn-cgi/image/format=auto,quality=80,width=1200,height=900,fit=cover/article/main-picture/60479ae773a23365656927.jpg", elena));
        mascotaRepository.save(new Mascota("Simba", "Gato", "Siamés", 4, "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Siamese201.jpg?itok=j9A2IvjN", ricardo));
        mascotaRepository.save(new Mascota("Oliver", "Gato", "Maine Coon", 2, "https://www.zooplus.es/magazine/wp-content/uploads/2018/08/maine-coon-3.jpg", sofia));
        mascotaRepository.save(new Mascota("Nala", "Gato", "Bengalí", 5, "https://0d2ujxjiqkxw.cdn.shift8web.com/wp-content/uploads/Gato-bengal%C3%AD.jpg", fernando));
        mascotaRepository.save(new Mascota("Felix", "Gato", "Siberiano", 1,
                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/Siberiano.jpg", gabriela));

        

    }
}
