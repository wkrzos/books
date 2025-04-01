package com.example.books.mapper;

import com.example.books.dto.AuthorDTO;
import com.example.books.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);
}
