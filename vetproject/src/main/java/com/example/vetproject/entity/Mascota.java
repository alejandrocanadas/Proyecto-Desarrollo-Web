package com.example.vetproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import jakarta.persistence.*;


@Entity
@Table(name = "PETS_TABLE")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = true) 
    private Veterinario veterinario;

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicamento> medicamentos;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Cada mascota tiene un dueño
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

    private Integer edad; // Se mantiene como Integer para permitir valores nulos

    private String imagenUrl; // Permite valores nulos

    // Constructores
    public Mascota(Long id, String nombre, String tipo, String raza, int edad, String imagenUrl, Cliente cliente) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.edad = edad;
        this.imagenUrl = imagenUrl;
        this.cliente = cliente;
    }

    // Constructor sin ID
    public Mascota(String nombre, String tipo, String raza, int edad, String imagenUrl, Cliente cliente) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.edad = edad;
        this.imagenUrl = imagenUrl;
        this.cliente = cliente;
    }

    // Constructor vacío
    public Mascota() {
        this.medicamentos = new ArrayList<>();
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

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    // Métodos para añadir elementos
    public void addMedicamento(Medicamento medicamento) {
        if (this.medicamentos == null) {
            this.medicamentos = new ArrayList<>();
        }
        this.medicamentos.add(medicamento);
    }
}



