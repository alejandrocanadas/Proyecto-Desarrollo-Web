package com.example.vetproject.error;

public class NotFoundMascotException extends RuntimeException {
    private Long id;

    public NotFoundMascotException(Long id) {
        this.id = id;
    }   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}