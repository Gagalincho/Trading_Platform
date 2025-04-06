package com.example.backend.error.user;

public class FieldsCannotBeEmptyException extends RuntimeException {
    
    public FieldsCannotBeEmptyException(String message) {
        super(message);
    }
    
}
