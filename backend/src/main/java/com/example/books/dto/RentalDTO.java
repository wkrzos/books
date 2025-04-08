package com.example.books.dto;

import java.time.LocalDateTime;

public record RentalDTO(
        Integer id,
        BookDTO bookDTO,
        UserDTO userDTO,
        LocalDateTime rentalDate,
        LocalDateTime returnDate
) {
}
