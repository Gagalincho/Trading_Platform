package com.example.backend.error.user;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

}
