package com.example.backend.error.user;

public class NoUserWithSuchIdExistsException extends RuntimeException {

    public NoUserWithSuchIdExistsException(String message) {
        super(message);
    }

}
