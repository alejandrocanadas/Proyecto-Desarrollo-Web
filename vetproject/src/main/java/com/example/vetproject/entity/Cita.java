package com.example.vetproject.entity;

import java.util.Date;

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
@Table(name = "CITA_TABLE")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    // Constructor sin ID
    public Cita(Cliente cliente, Mascota mascota, Veterinario veterinario, Date fecha) {
        this.cliente = cliente;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fecha = fecha;
    }

}
