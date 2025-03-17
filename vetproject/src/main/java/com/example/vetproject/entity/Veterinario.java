package com.example.vetproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import jakarta.persistence.*;

@Entity
@Table(name = "VETS_TABLE")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(nullable = false, unique = true)
    private String telefono;

    @NotNull
    @Email(message = "Debe ingresar un email válido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false, unique = true)
    private String usuario;

    @NotNull
    @Column(nullable = false)
    private String contrasena;

    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas;

    // Constructor vacío
    public Veterinario() {
        
    }

    // Constructor sin ID
    public Veterinario(String nombre, String telefono, String email, String usuario, String contrasena) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.mascotas = new ArrayList<>();
    }

    // Constructor con ID
    public Veterinario(Long id, String nombre, String telefono, String email, String usuario, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.mascotas = new ArrayList<>();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public List<Mascota> getMascotas() { return mascotas; }
    public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }

    // Método para añadir mascotas
    public void addMascota(Mascota mascota) {
        if (this.mascotas == null) {
            this.mascotas = new ArrayList<>();
        }
        this.mascotas.add(mascota);
        mascota.setVeterinario(this); // Relación bidireccional
    }
}
