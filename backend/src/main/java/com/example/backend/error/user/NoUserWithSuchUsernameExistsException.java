package com.example.backend.error.user;

public class NoUserWithSuchUsernameExistsException extends RuntimeException {

    public NoUserWithSuchUsernameExistsException(String message) {
        super(message);
    }

}
