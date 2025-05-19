package com.example.vetproject.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MED_TABLE")
public class Medicamento {
    @Id
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(nullable = false)
    private double precioventa;

    @NotNull
    @Column(nullable = false)
    private double preciocompra;

    @NotNull
    @Column(nullable = false)
    private int stock; // Como es un int, no puede ser null por defecto.

    @NotNull
    @Column(nullable = false)
    private int uvendidas; // Como es un int, no puede ser null por defecto.
    

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tratamiento_id", nullable = true)
    private Tratamiento tratamiento;

    public Medicamento(String nombre, double preciocompra, double precioventa, int stock, int uvendidas) {
        this.nombre = nombre;
        this.preciocompra = preciocompra;
        this.precioventa = precioventa;
        this.stock = stock;
        this.uvendidas = uvendidas;
    }

    public Medicamento(String nombre2, double preciocompra2, double precioventa2, int stock2, int uvendidas2,
            Object object) {
        //TODO Auto-generated constructor stub
    }

}




