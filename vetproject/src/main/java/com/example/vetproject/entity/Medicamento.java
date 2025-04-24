package com.example.vetproject.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "MED_TABLE")
public class Medicamento {
    @Id
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(nullable = false)
    private double precioventa;

    @NotNull
    @Column(nullable = false)
    private double preciocompra;

    @NotNull
    @Column(nullable = false)
    private int stock; // Como es un int, no puede ser null por defecto.

    @NotNull
    @Column(nullable = false)
    private int uvendidas; // Como es un int, no puede ser null por defecto.
    

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tratamiento_id", nullable = true)
    private Tratamiento tratamiento;



    public Medicamento(Long id, String nombre, double preciocompra, double precioventa, int stock, int uvendidas, Tratamiento tratamiento) {
        this.id = id;
        this.nombre = nombre;
        this.preciocompra = preciocompra;
        this.precioventa = precioventa;
        this.stock = stock;
        this.uvendidas = uvendidas;
        this.tratamiento = tratamiento;
    }

    public Medicamento(String nombre, double preciocompra, double precioventa, int stock, int uvendidas) {
        this.nombre = nombre;
        this.preciocompra = preciocompra;
        this.precioventa = precioventa;
        this.stock = stock;
        this.uvendidas = uvendidas;
    }

    public Medicamento() {}

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

    public double getPrecioVenta() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa = precioventa;
    }

    public double getPrecioCompra() {
        return preciocompra;
    }

    public void setPrecioCompra(double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public int getSotck() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getUvendidas() {
        return uvendidas;
    }

    public void setUvendidas(int uvendidas) {
        this.uvendidas = uvendidas;
    }
    
    
}




