package com.example.books.exception;

public class BookNotFound extends NotFoundException {
    public BookNotFound(Integer id) {
        super(String.format("Book with ID: %s not found", id));
    }
}
