package com.example.user_service.configuration.customExceptions;

public class UserAlreadyExistsException  extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}