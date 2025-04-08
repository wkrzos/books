package com.example.books.dto;

public record UserDTO(
        Integer id,
        String username,
        String password,
        String email,
        String name,
        String surname
) {
}
