package com.example.books.seeder;

import com.example.books.entity.Author;
import com.example.books.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorSeeder {

    private final AuthorRepository authorRepository;

    public void seedAuthors() {

        if (authorRepository.count() == 0) {
            log.info("Rozpoczynanie seedowania autorów.");

            List<Author> authors = List.of(
                    new Author(1, "George", "Orwell", List.of()),
                    new Author(2, "Jane", "Austen", List.of()),
                    new Author(3, "Fyodor", "Dostoevsky", List.of()),
                    new Author(4, "Harper", "Lee", List.of()),
                    new Author(5, "J.D.", "Salinger", List.of()),
                    new Author(6, "F. Scott", "Fitzgerald", List.of()),
                    new Author(7, "Herman", "Melville", List.of()),
                    new Author(8, "Leo", "Tolstoy", List.of())
            );

            authorRepository.saveAll(authors);
            log.info("Autorzy zostali pomyślnie zapisani w bazie.");
        } else {
            log.info("Autorzy już istnieją w bazie danych, seedowanie zostało pominięte.");
        }
    }
}
