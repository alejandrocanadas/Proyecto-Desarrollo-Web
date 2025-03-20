package com.example.vetproject.entity;

import jakarta.persistence.*;

import java.util.List;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TRATAMIENTO_TABLE")
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;
    

    @NotNull
    private String idMascota;
    
    @NotNull
    private String idTratamiento;
    
    @NotNull
    private String idMedicamento;

    @OneToMany(mappedBy = "tratamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicamento> medicamentos;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;

    // Constructor vacío
    public Tratamiento() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdMascota() { return idMascota; }
    public void setIdMascota(String idMascota) { this.idMascota = idMascota; }

    public String getIdTratamiento() { return idTratamiento; }
    public void setIdTratamiento(String idTratamiento) { this.idTratamiento = idTratamiento; }

    public String getIdMedicamento() { return idMedicamento; }
    public void setIdMedicamento(String idMedicamento) { this.idMedicamento = idMedicamento; }


    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void addMedicamento(Medicamento medicamento) {
        if (this.medicamentos == null) {
            this.medicamentos = new java.util.ArrayList<>();
        }
        this.medicamentos.add(medicamento);
    }
    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }

    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }

    public String mostrarTratamiento() {
        return "Tratamiento: " + nombre;
    }
}
