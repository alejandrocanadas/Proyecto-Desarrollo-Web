package com.example.vetproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PETS_TABLE")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    private List<Tratamiento> tratamientos;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

   
    @NotNull
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(nullable = false)
    private String tipo;

    @NotNull
    @Column(nullable = false)
    private String raza;

    @NotNull
    @Column(nullable = false)
    private String sexo;

    @NotNull
    @Column(nullable = false)
    private String estado;

    private Integer edad; // Se mantiene como Integer para permitir valores nulos

    private String imagenUrl; // Permite valores nulos

    // Constructores
    public Mascota(Long id, String nombre, String tipo, String raza, String sexo, String estado, int edad, String imagenUrl, Cliente cliente) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.sexo = sexo;
        this.estado = estado;
        this.edad = edad;
        this.imagenUrl = imagenUrl;
        this.cliente = cliente;
    }

    // Constructor sin ID
    public Mascota(String nombre, String tipo, String raza, String sexo, String estado, int edad, String imagenUrl, Cliente cliente) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.sexo = sexo;
        this.estado = estado;
        this.edad = edad;
        this.imagenUrl = imagenUrl;
        this.cliente = cliente;
    }

    // Métodos para añadir elementos
    public void addTratamiento(Tratamiento tratamiento) {
        if (this.tratamientos == null) {
            this.tratamientos = new ArrayList<>();
        }
        this.tratamientos.add(tratamiento);
    }
}



