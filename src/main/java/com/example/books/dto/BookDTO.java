package com.example.books.dto;

public record BookDTO(
        Integer id,
        String title,
        Integer authorID,
        Integer pages,
        boolean isAvailable
) {
}
