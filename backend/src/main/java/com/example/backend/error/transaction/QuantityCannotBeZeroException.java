package com.example.backend.error.transaction;

public class QuantityCannotBeZeroException extends RuntimeException {

    public QuantityCannotBeZeroException(String message) {
        super(message);
    }
    
}
