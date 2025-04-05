package com.example.backend.error.transaction;

public class NoTransactionWithSuchIdException extends RuntimeException {
    
    public NoTransactionWithSuchIdException(String message) {
        super(message);
    }
    
}
