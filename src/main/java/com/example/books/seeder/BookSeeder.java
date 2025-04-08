package com.example.books.seeder;

import com.example.books.entity.Author;
import com.example.books.entity.Book;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookSeeder {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public void seedBooks() {

        if (bookRepository.count() == 0) {
            log.info("Rozpoczynanie seedowania książek.");

            Optional<Author> orwell = authorRepository.findById(1);
            Optional<Author> austen = authorRepository.findById(2);
            Optional<Author> dostoevsky = authorRepository.findById(3);
            Optional<Author> harperLee = authorRepository.findById(4);
            Optional<Author> salinger = authorRepository.findById(5);
            Optional<Author> fitzgerald = authorRepository.findById(6);
            Optional<Author> melville = authorRepository.findById(7);
            Optional<Author> tolstoy = authorRepository.findById(8);

            if (orwell.isPresent() && austen.isPresent() && dostoevsky.isPresent() &&
                    harperLee.isPresent() && salinger.isPresent() && fitzgerald.isPresent() &&
                    melville.isPresent() && tolstoy.isPresent()) {
                log.info("Autorzy zostali znalezieni, rozpoczynam dodawanie książek.");

                List<Book> books = List.of(
                        new Book(1, "1984", orwell.get(), 328, true, List.of()),
                        new Book(2, "Animal Farm", orwell.get(), 112, true, List.of()),
                        new Book(5, "Homage to Catalonia", orwell.get(), 220, true, List.of()),
                        new Book(6, "Keep the Aspidistra Flying", orwell.get(), 291, false, List.of()),
                        new Book(7, "Burmese Days", orwell.get(), 330, true, List.of()),
                        new Book(3, "Pride and Prejudice", austen.get(), 432, true, List.of()),
                        new Book(4, "Sense and Sensibility", austen.get(), 350, true, List.of()),
                        new Book(8, "Emma", austen.get(), 450, true, List.of()),
                        new Book(9, "Mansfield Park", austen.get(), 412, false, List.of()),
                        new Book(10, "Northanger Abbey", austen.get(), 325, true, List.of()),
                        new Book(11, "Persuasion", austen.get(), 290, true, List.of()),
                        new Book(12, "Crime and Punishment", dostoevsky.get(), 671, true, List.of()),
                        new Book(13, "The Brothers Karamazov", dostoevsky.get(), 796, true, List.of()),
                        new Book(14, "The Idiot", dostoevsky.get(), 450, true, List.of()),
                        new Book(15, "Notes from Underground", dostoevsky.get(), 220, false, List.of()),
                        new Book(16, "Demons", dostoevsky.get(), 706, true, List.of()),
                        new Book(17, "The Gambler", dostoevsky.get(), 245, true, List.of()),
                        new Book(18, "To Kill a Mockingbird", harperLee.get(), 281, true, List.of()),
                        new Book(19, "The Catcher in the Rye", salinger.get(), 214, false, List.of()),
                        new Book(20, "The Great Gatsby", fitzgerald.get(), 180, true, List.of()),
                        new Book(21, "Moby-Dick", melville.get(), 635, true, List.of()),
                        new Book(22, "War and Peace", tolstoy.get(), 1225, true, List.of())
                );

                bookRepository.saveAll(books);
                log.info("Książki zostały pomyślnie zapisane w bazie.");
            } else {
                log.error("Nie udało się znaleźć wszystkich autorów. Seeder nie mógł dodać książek.");
            }
        } else {
            log.info("Książki już istnieją w bazie, seedowanie zostało pominięte.");
        }
    }
}
