package com.example.books.mapper;

import com.example.books.dto.BookDTO;
import com.example.books.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "available", source = "isAvailable")
    Book toEntity(BookDTO bookDTO);

    @Mapping(target = "authorID", source = "author.id")
    @Mapping(target = "isAvailable", source = "available")
    BookDTO toDTO(Book book);
}
