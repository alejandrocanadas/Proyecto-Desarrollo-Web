package com.example.vetproject.dto;

public class AsignacionTratamientoDTO {
    private Long mascotaId;
    private Long tratamientoId;
    private Long veterinarioId;

    // Constructor vacío
    public AsignacionTratamientoDTO() {}

    // Constructor con parámetros
    public AsignacionTratamientoDTO(Long mascotaId, Long tratamientoId, Long veterinarioId) {
        this.mascotaId = mascotaId;
        this.tratamientoId = tratamientoId;
        this.veterinarioId = veterinarioId;
    }

    // Getters y Setters
    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }

    public Long getTratamientoId() {
        return tratamientoId;
    }

    public void setTratamientoId(Long tratamientoId) {
        this.tratamientoId = tratamientoId;
    }

    public Long getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }
} 