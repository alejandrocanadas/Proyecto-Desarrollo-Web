package com.example.vetproject.entity;

import java.util.List;

public class Mascota {
    int id;
    String nombre;
    String tipo;
    String raza;
    int edad;
    String imagenUrl;

    //constructores
    public Mascota(int id, String nombre, String tipo, String raza, int edad, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.edad = edad;
        this.imagenUrl = imagenUrl; // Asegura que la URL esté bien
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl; // Agregar la ruta correctamente
    }
    //getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }
    public int getId() {
        return id;
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

 
    
}
