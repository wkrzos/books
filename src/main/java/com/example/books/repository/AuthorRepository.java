package com.example.books.repository;

import com.example.books.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByNameAndSurname(String name, String surname);
}
