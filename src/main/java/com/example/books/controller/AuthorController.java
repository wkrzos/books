package com.example.books.controller;

import com.example.books.dto.AuthorDTO;
import com.example.books.service.contract.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAll();

        return ResponseEntity
                .ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Integer id) {
        AuthorDTO author = authorService.getById(id);

        return ResponseEntity
                .ok(author);
    }

    @GetMapping("/find")
    public ResponseEntity<AuthorDTO> getAuthorByName(@RequestParam String name, @RequestParam String surname) {
        AuthorDTO author = authorService.getByNameAndSurname(name, surname);

        return ResponseEntity
                .ok(author);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO author) {
        AuthorDTO createdAuthor = authorService.create(author);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAuthor);
    }

    @PostMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Integer id, @RequestBody AuthorDTO author) {
        AuthorDTO updatedAuthor = authorService.update(id, author);

        return ResponseEntity
                .ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        authorService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
