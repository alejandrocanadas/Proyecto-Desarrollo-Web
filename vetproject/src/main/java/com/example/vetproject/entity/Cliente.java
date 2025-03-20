package com.example.vetproject.entity;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIENT_TABLE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cita> citas;
    
    @NotNull
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(nullable = false, unique=true)
    private String usuario; // Asegurar que no haya dos usuarios iguales

    @NotNull
    @Column(nullable = false)
    private String apellido;

    @NotNull
    @Column(nullable = false)
    private String contrasena;

    @NotNull
    @Column(nullable = false, unique = true)
    private String telefono;

    @NotNull
    @Column(nullable = false, unique = true)
    @Email(message = "Debe ingresar un email válido")
    private String email;

    // Constructores
    public Cliente(Long id, String nombre, String usuario, String apellido, String telefono, String email, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Cliente(String nombre, String usuario, String apellido, String telefono, String email, String contrasena) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Cliente() {
        // Constructor vacío
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    // Métodos para añadir elementos a las listas, inicializándolas solo cuando sea necesario
    public void addMascota(Mascota mascota) {
        if (this.mascotas == null) {
            this.mascotas = new java.util.ArrayList<>();
        }
        this.mascotas.add(mascota);
    }

    public void addCita(Cita cita) {
        if (this.citas == null) {
            this.citas = new java.util.ArrayList<>();
        }
        this.citas.add(cita);
    }

    
}
