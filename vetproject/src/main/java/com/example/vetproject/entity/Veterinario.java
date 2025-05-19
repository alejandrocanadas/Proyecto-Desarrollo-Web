package com.example.vetproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.checkerframework.checker.units.qual.mPERs;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "VETS_TABLE")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

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
    @Column(nullable = false)
    private String estado;

    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tratamiento> tratamientos;

    // Constructor sin ID
    public Veterinario(String nombre, String telefono, String email, String estado) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.tratamientos = new ArrayList<>();
        this.estado = estado;
    }

    // Constructor con ID
    public Veterinario(Long id, String nombre, String telefono, String email, String estado) {   
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.tratamientos = new ArrayList<>();
        this.estado = estado;
    }

    // Método para añadir tratamientos
    public void addTratamiento(Tratamiento tratamiento) {
        if (this.tratamientos == null) {
            this.tratamientos = new ArrayList<>();
        }
        this.tratamientos.add(tratamiento);
        tratamiento.setVeterinario(this); // Relación bidireccional
    }
}
