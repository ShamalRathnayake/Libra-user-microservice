package com.example.user_service.configuration.customExceptions;

public class UserNotFoundWIthIdException extends RuntimeException {
    public UserNotFoundWIthIdException(String message) {
        super(message);
    }
}