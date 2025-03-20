package com.example.vetproject.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "CITA_TABLE")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    // Constructor vacío
    public Cita() {}

    // Constructor sin ID
    public Cita(Cliente cliente, Mascota mascota, Veterinario veterinario, Date fecha) {
        this.cliente = cliente;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fecha = fecha;
    }

    // Constructor con ID
    public Cita(Long id, Cliente cliente, Mascota mascota, Veterinario veterinario, Date fecha) {
        this.id = id;
        this.cliente = cliente;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fecha = fecha;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }

    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
