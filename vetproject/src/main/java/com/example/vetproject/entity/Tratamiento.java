package com.example.vetproject.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name = "TRATAMIENTO_TABLE")
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    
    @OneToOne(mappedBy = "tratamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Medicamento medicamento;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = true)
    private Mascota mascota;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;

    // Constructor vacío
    public Tratamiento() {}

    public Tratamiento(String nombre, Veterinario veterinario, Mascota mascota, Medicamento medicamento) {        
        this.medicamento = medicamento;
        this.veterinario = veterinario;
        this.mascota = mascota;
        this.nombre = nombre;
        this.fecha = LocalDateTime.now();
    }
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
        if (medicamento != null) {
            medicamento.setTratamiento(this);
        }
    }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }

    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }

    public String mostrarTratamiento() {
        return "Tratamiento: " + nombre;
    }
}
