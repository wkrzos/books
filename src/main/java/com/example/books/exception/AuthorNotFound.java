package com.example.books.exception;

public class AuthorNotFound extends NotFoundException {
    public AuthorNotFound(Integer id) {
        super(String.format("Author with ID: %s not found", id));
    }
    public AuthorNotFound(String name, String surname) {
        super(String.format("Author with name: %s %s not found", name, surname));
    }
}
