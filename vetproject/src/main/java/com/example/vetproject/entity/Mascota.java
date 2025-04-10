package com.example.vetproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
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

    // Constructor vacío
    public Mascota() {
        this.tratamientos = new ArrayList<>();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getEdad() {
        return edad != null ? edad : 0;  // Devuelve 0 si es null
    }
    

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

    // Métodos para añadir elementos
    public void addTratamiento(Tratamiento tratamiento) {
        if (this.tratamientos == null) {
            this.tratamientos = new ArrayList<>();
        }
        this.tratamientos.add(tratamiento);
    }
}



