package com.example.vetproject.entity;

import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PETS_TABLE")
public class Mascota {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String tipo;
    private String raza;
    private int edad;
    private String imagenUrl;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //constructores
    public Mascota(Long id, String nombre, String tipo, String raza, int edad, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.edad = edad;
        this.imagenUrl = imagenUrl; 
    }

    //constructor sin id
    public Mascota(String nombre, String tipo, String raza, int edad, String imagenUrl, Cliente cliente) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.edad = edad;
        this.imagenUrl = imagenUrl;
        this.cliente = cliente;
    }

    //constructor vacío
    public Mascota() {
        
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl; 
    }
    //getters y setters

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
}   
