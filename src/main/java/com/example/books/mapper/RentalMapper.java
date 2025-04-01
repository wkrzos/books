package com.example.books.mapper;

import com.example.books.dto.RentalDTO;
import com.example.books.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BookMapper.class, UserMapper.class})
public interface RentalMapper {

    @Mapping(target = "user", source = "userDTO")
    @Mapping(target = "book", source = "bookDTO")
    Rental toEntity(RentalDTO rentalDTO);

    @Mapping(target = "bookDTO", source = "book")
    @Mapping(target = "userDTO", source = "user")
    RentalDTO toDTO(Rental rental);
}
