package com.example.vetproject.error;

public class NotFoundException extends RuntimeException {
    private int id;

    public NotFoundException(int id) {
        this.id = id;
    }   

    int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}