package com.example.vetproject.entity;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "CLIENT_TABLE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;
    
    @JsonIgnore // lo usamos para que el Json no tenga que retornar la lista de mascotas anidadas al cliente ya que se genera un bulce en donde el cliente tiene mascotas pero las mascotas tambien tienen un cliente
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Mascota> mascotas;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Cita> citas;
    
    @NotNull
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(nullable = false)
    private String apellido;

    @NotNull
    @Column(nullable = false, unique = true)
    private String telefono;

    @NotNull
    @Column(nullable = false, unique = true)
    @Email(message = "Debe ingresar un email válido")
    private String email;

    // Constructores
    public Cliente(Long id, String nombre, String apellido, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(String nombre, String apellido, String telefono, String email) {
        this.nombre = nombre;

        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;

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
