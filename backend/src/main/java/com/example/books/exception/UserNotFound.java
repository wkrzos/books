package com.example.books.exception;

public class UserNotFound extends NotFoundException {
    public UserNotFound(Integer id) {
        super(String.format("User with ID: %s not found", id));
    }

    public UserNotFound(String username) {
        super(String.format("User with username: %s not found", username));
    }
}
