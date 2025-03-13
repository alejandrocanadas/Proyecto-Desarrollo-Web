package com.example.vetproject.error;

public class NotFoundClientException extends RuntimeException {
    private Long id;

    public NotFoundClientException(Long id) {
        this.id = id;
    }   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
