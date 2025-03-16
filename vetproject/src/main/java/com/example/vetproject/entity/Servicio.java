package com.example.vetproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SERVICIO_TABLE")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;

    private String nombre;
    private Double costo;
    private String descripcion;

    // Constructor vacío
    public Servicio() {}

    // Constructor con ID
    public Servicio(Long id, String nombre, Double costo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
    }

    // Constructor sin ID
    public Servicio(String nombre, Double costo, String descripcion) {
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public void addCliente(Cliente cliente) { this.cliente = cliente; }
}
