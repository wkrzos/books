package com.example.books.service.implementation;

import com.example.books.dto.RentalDTO;
import com.example.books.entity.Book;
import com.example.books.entity.Rental;
import com.example.books.entity.User;
import com.example.books.exception.BookNotFound;
import com.example.books.exception.RentalNotFound;
import com.example.books.exception.UserNotFound;
import com.example.books.mapper.RentalMapper;
import com.example.books.repository.BookRepository;
import com.example.books.repository.RentalRepository;
import com.example.books.repository.UserRepository;
import com.example.books.service.contract.RentalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public RentalDTO rentBook(Integer bookId, String username) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFound(bookId));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFound(username));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available"); ///TU POWINIEN BYĆ JAKIS CUSTOM EXCEPTION
        }

        book.setAvailable(false);
        Rental rental = new Rental();

        rental.setBook(book);
        rental.setUser(user);
        rental.setRentalDate(LocalDateTime.now());

        rentalRepository.save(rental);
        bookRepository.save(book);

        log.info("Book with ID: {} rented", bookId);

        return rentalMapper.toDTO(rental);
    }

    @Override
    public RentalDTO returnBook(Integer id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFound(id));

        if(rental.getReturnDate() != null) {
            throw new RuntimeException("Book is already returned");
        }

        rental.setReturnDate(LocalDateTime.now());

        Book book = rental.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        rentalRepository.save(rental);

        log.info("Book with ID: {} returned", book.getId());

        return rentalMapper.toDTO(rental);
    }
}
