package com.example.books.service.contract;

import com.example.books.dto.RentalDTO;

public interface RentalService {

    RentalDTO rentBook(Integer bookId, String username);

    RentalDTO returnBook(Integer bookId);
}
