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
        mascotas.put(2, new Mascota("Max", "Perro", "Labrador", 4, "mascota1.jpg" ));
        mascotas.put(3, new Mascota("Bella", "Perro", "Bulldog Francés", 2, "mascota1.jpg" ));
        mascotas.put(4, new Mascota("Rocky", "Perro", "Pastor Alemán", 5, "mascota1.jpg" ));
        mascotas.put(5, new Mascota("Toby", "Perro", "Beagle", 1, "mascota1.jpg" ));

        mascotas.put(6, new Mascota("Milo", "Gato", "Persa", 3, "mascota1.jpg" ));
        mascotas.put(7, new Mascota("Simba", "Gato", "Siamés", 4, "mascota1.jpg" ));
        mascotas.put(8, new Mascota("Oliver", "Gato", "Maine Coon", 2, "mascota1.jpg" ));
        mascotas.put(9, new Mascota("Nala", "Gato", "Bengalí", 5, "mascota1.jpg" ));
        mascotas.put(10, new Mascota("Felix", "Gato", "Siberiano", 1, "mascota1.jpg" ));
    }

    public Mascota findById(Integer id) {
        return mascotas.get(id);
    }

    public Collection<Mascota> findAll() {
        return mascotas.values();
    }

}
