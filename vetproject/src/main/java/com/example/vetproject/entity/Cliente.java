package com.example.vetproject.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENT_TABLE")
public class Cliente {
    @OneToMany(mappedBy = "cliente")
    private List<Mascota> mascotas = new ArrayList<Mascota>(); 

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String usuario;
    private String apellido;
    private String telefono;
    private String email;
    private String contrasena;

    public Cliente(String id, String nombre, String usuario, String apellido, String telefono, String email, String contrasena) {
        this.id = Long.parseLong(id);
        this.nombre = nombre;
        this.usuario = usuario;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Cliente (String nombre, String usuario, String apellido, String telefono, String email, String contrasena) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
        // Constructor sin id
    }

    public Cliente(){
        // Constructor vacío
    }

    public Long getId() {
        return id;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    

}
