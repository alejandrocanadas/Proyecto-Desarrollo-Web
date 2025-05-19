package com.example.vetproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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

    public Tratamiento(String nombre, Veterinario veterinario, Mascota mascota, Medicamento medicamento) {        
        this.medicamento = medicamento;
        this.veterinario = veterinario;
        this.mascota = mascota;
        this.nombre = nombre;
        this.fecha = LocalDateTime.now();
    }
    
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
        if (medicamento != null) {
            medicamento.setTratamiento(this);
        }
    }

    public String mostrarTratamiento() {
        return "Tratamiento: " + nombre;
    }
}
