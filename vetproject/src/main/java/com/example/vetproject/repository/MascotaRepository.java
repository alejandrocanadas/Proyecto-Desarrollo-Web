package com.example.vetproject.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.vetproject.entity.Mascota;

@Repository
public class MascotaRepository {
    private Map<Integer, Mascota> mascotas = new HashMap<>();

    public MascotaRepository() {
        mascotas.put(1, new Mascota("Luna", "Perro", "Golden Retriever", 3, "https://assets.elanco.com/8e0bf1c2-1ae4-001f-9257-f2be3c683fb1/79c7911f-f0d3-4884-a02c-481b73f14e1b/Perro%20en%20los%20campos.jpeg?w=3840&q=75&auto=format" ));
        mascotas.put(2, new Mascota("Max", "Perro", "Labrador", 4, "https://mp.reshift.nl/zoom/ce270323-a8dc-4381-a730-888ad9c1d1ec-labrador-pup.jpeg?w=460" ));
        mascotas.put(3, new Mascota("Bella", "Perro", "Bulldog Francés", 2, "https://www.zooplus.es/magazine/wp-content/uploads/2019/06/franz%C3%B6sische-bulldogge-1024x683.jpg" ));
        mascotas.put(4, new Mascota("Rocky", "Perro", "Pastor Alemán", 5, "https://www.zooplus.es/magazine/wp-content/uploads/2019/03/deutscher-sch%C3%A4ferhund.jpg" ));
        mascotas.put(5, new Mascota("Toby", "Perro", "Beagle", 1, "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/Beagle-1.jpg" ));
        mascotas.put(6, new Mascota("Milo", "Gato", "Persa", 3, "https://cdn.wamiz.fr/cdn-cgi/image/format=auto,quality=80,width=1200,height=900,fit=cover/article/main-picture/60479ae773a23365656927.jpg" ));
        mascotas.put(7, new Mascota("Simba", "Gato", "Siamés", 4, "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Siamese201.jpg?itok=j9A2IvjN" ));
        mascotas.put(8, new Mascota("Oliver", "Gato", "Maine Coon", 2, "https://www.zooplus.es/magazine/wp-content/uploads/2018/08/maine-coon-3.jpg" ));
        mascotas.put(9, new Mascota("Nala", "Gato", "Bengalí", 5, "https://0d2ujxjiqkxw.cdn.shift8web.com/wp-content/uploads/Gato-bengal%C3%AD.jpg" ));
        mascotas.put(10, new Mascota("Felix", "Gato", "Siberiano", 1, "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/Siberiano.jpg" ));
    }

    public Mascota findById(Integer id) {
        return mascotas.get(id);
    }

    public Collection<Mascota> findAll() {
        return mascotas.values();
    }

}
