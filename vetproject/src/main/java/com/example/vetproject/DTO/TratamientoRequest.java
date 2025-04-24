package com.example.vetproject.DTO;

public class TratamientoRequest {
    private Long mascotaId;
    private Long veterinarioId;
    private Long medicamentoId;

    public Long getMascotaId() {
        return mascotaId;
    }
    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }
    public Long getVeterinarioId() {
        return veterinarioId;
    }
    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }
    public Long getMedicamentoId() {
        return medicamentoId;
    }
    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
    }
    
}
