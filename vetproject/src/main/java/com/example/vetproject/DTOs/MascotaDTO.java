package com.example.vetproject.DTOs;

import lombok.Data;

@Data
public class MascotaDTO {
    private Long id;
    private String nombre;
    private String tipo;
    private String raza;
    private String sexo;
    private String estado;
    private Integer edad;
    private String imagenUrl;
} 