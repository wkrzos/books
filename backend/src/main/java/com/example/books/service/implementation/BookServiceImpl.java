package com.example.books.service.implementation;


import com.example.books.dto.BookDTO;
import com.example.books.entity.Author;
import com.example.books.entity.Book;
import com.example.books.exception.AuthorNotFound;
import com.example.books.exception.BookNotFound;
import com.example.books.mapper.BookMapper;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import com.example.books.service.contract.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    @Override
    public BookDTO create(BookDTO dto) {
        Author author = authorRepository.findById(dto.authorID())
                .orElseThrow(() -> new AuthorNotFound(dto.authorID()));

        Book book = bookMapper.toEntity(dto);

        book.setAuthor(author);

        bookRepository.save(book);

        log.info("Created book: {}", book);

        return bookMapper.toDTO(book);
    }

    @Override
    public void delete(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFound(id));

        bookRepository.delete(book);

        log.info("Deleted book: {}", book);
    }

    @Override
    public BookDTO update(Integer id, BookDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFound(id));

        Author author = authorRepository.findById(dto.authorID())
                .orElseThrow(() -> new AuthorNotFound(dto.authorID()));

        book.setId(dto.id());
        book.setAuthor(author);
        book.setTitle(dto.title());
        book.setPages(dto.pages());
        book.setAvailable(dto.isAvailable());

        bookRepository.save(book);

        log.info("Updated book: {}", book);

        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO getById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFound(id));

        log.info("Retrieved book: {}", book);

        return bookMapper.toDTO(book);
    }

    @Override
    public List<BookDTO> getAll(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);

        log.info("Retrieved all books: {}", books);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }
}
