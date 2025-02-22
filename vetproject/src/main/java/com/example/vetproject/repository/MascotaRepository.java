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
        mascotas.put(1, new Mascota("Luna", "Perro", "Golden Retriever", 3, null));
        mascotas.put(2, new Mascota("Max", "Perro", "Labrador", 4, null));
        mascotas.put(3, new Mascota("Bella", "Perro", "Bulldog Francés", 2, null));
        mascotas.put(4, new Mascota("Rocky", "Perro", "Pastor Alemán", 5, null));
        mascotas.put(5, new Mascota("Toby", "Perro", "Beagle", 1, null));

        mascotas.put(6, new Mascota("Milo", "Gato", "Persa", 3, null));
        mascotas.put(7, new Mascota("Simba", "Gato", "Siamés", 4, null));
        mascotas.put(8, new Mascota("Oliver", "Gato", "Maine Coon", 2, null));
        mascotas.put(9, new Mascota("Nala", "Gato", "Bengalí", 5, null));
        mascotas.put(10, new Mascota("Felix", "Gato", "Siberiano", 1, null));
    }

    public Mascota findById(Integer id) {
        return mascotas.get(id);
    }

    public Collection<Mascota> findAll() {
        return mascotas.values();
    }

}
