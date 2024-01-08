package com.example.generic.crud.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public <T> EntityNotFoundException(Class<T> clazz) {
        super(clazz.getSimpleName() + " not found");
    }
}
