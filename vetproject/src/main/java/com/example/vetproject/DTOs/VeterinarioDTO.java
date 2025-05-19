package com.example.vetproject.DTOs;

import lombok.Data;

@Data
public class VeterinarioDTO {
    private Long id;
    private String nombre;
    private String telefono;
    private String email;
    private String usuario;
    private String estado;
} 