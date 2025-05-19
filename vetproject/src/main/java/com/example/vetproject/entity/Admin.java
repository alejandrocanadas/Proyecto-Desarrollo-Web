package com.example.vetproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
@Table(name = "ADMIN_TABLE")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;
    
    @NotNull
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(nullable = false, unique = true)
    private String telefono;

    @NotNull
    @Email(message = "Debe ingresar un email válido")
    @Column(nullable = false, unique = true)
    private String email;

    // Constructor sin ID
    public Admin(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }
}
