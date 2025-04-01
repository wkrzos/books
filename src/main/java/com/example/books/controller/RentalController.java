package com.example.books.controller;

import com.example.books.dto.RentalDTO;
import com.example.books.service.contract.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
@AllArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping("/{bookId}/rent")
    public ResponseEntity<RentalDTO> rent(@PathVariable Integer bookId, @RequestParam String username) {
        RentalDTO rental = rentalService.rentBook(bookId, username);

        return ResponseEntity
                .ok(rental);
    }

    @PostMapping("/{rentalId}/return")
    public ResponseEntity<RentalDTO> returnBook(@PathVariable Integer rentalId) {
        RentalDTO rental = rentalService.returnBook(rentalId);

        return ResponseEntity
                .ok(rental);
    }
}
