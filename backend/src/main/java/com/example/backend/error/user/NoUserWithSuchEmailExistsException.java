package com.example.backend.error.user;

public class NoUserWithSuchEmailExistsException extends RuntimeException {

    public NoUserWithSuchEmailExistsException(String message) {
        super(message);
    }

}