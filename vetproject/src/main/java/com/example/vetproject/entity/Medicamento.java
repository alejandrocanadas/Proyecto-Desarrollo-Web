package com.example.vetproject.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MED_TABLE")
public class Medicamento {
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(nullable = false)
    private String descripcion;

    @NotNull
    @Column(nullable = false)
    private String dosis;

    @NotNull
    @Column(nullable = false)
    private int precio; // Como es un int, no puede ser null por defecto.

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tratamiento_id", nullable = false)
    private Tratamiento tratamiento;



    public Medicamento(Long id, String nombre, String descripcion, String dosis, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dosis = dosis;
        this.precio = precio;
    }

    public Medicamento(String nombre, String descripcion, String dosis, int precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dosis = dosis;
        this.precio = precio;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    
    
}




