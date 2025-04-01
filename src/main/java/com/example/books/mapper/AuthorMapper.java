package com.example.books.mapper;

import com.example.books.dto.AuthorDTO;
import com.example.books.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);
}
