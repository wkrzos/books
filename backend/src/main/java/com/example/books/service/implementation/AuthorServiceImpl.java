package com.example.books.service.implementation;

import com.example.books.dto.AuthorDTO;
import com.example.books.entity.Author;
import com.example.books.exception.AuthorNotFound;
import com.example.books.mapper.AuthorMapper;
import com.example.books.repository.AuthorRepository;
import com.example.books.service.contract.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorDTO create(AuthorDTO dto) {
        Author author = authorMapper.toEntity(dto);

        authorRepository.save(author);

        log.info("Created author: {}", author);

        return authorMapper.toDTO(author);
    }

    @Override
    public void delete(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFound(id));

        authorRepository.delete(author);

        log.info("Deleted author: {}", author);
    }

    @Override
    public AuthorDTO update(Integer id, AuthorDTO dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFound(id));

        author.setId(dto.id());
        author.setName(dto.name());
        author.setSurname(dto.surname());

        authorRepository.save(author);

        log.info("Updated author: {}", author);

        return authorMapper.toDTO(author);
    }

    @Override
    public AuthorDTO getById(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFound(id));

        log.info("Retrieved author: {}", author);

        return authorMapper.toDTO(author);
    }

    @Override
    public List<AuthorDTO> getAll(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);

        log.info("Retrieved all authors: {}", authors);

        return authors.stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @Override
    public AuthorDTO getByNameAndSurname(String name, String surname) {
        Author author = authorRepository.findByNameAndSurname(name, surname)
                .orElseThrow(() -> new AuthorNotFound(name, surname));

        log.info("Retrieved author by name and surname: {}", author);

        return authorMapper.toDTO(author);
    }
}
