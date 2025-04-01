package com.example.books.controller;

import com.example.books.dto.BookDTO;
import com.example.books.service.contract.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAll();

        return ResponseEntity
                .ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        BookDTO book = bookService.getById(id);

        return ResponseEntity
                .ok(book);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO book) {
        BookDTO createdBook = bookService.create(book);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdBook);
    }

    @PostMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer id, @RequestBody BookDTO book) {
        BookDTO updatedBook = bookService.update(id, book);

        return ResponseEntity
                .ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Integer id) {
        bookService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
