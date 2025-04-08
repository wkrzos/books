package com.example.books.exception;

public class RentalNotFound extends NotFoundException {
    public RentalNotFound(Integer id) {
        super(String.format("Rental with ID: %s not found", id));
    }
}
