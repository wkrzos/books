package com.example.books.service.contract;

import com.example.books.dto.AuthorDTO;
import com.example.books.service.generic.CRUDService;

public interface AuthorService extends CRUDService<AuthorDTO, Integer> {
    AuthorDTO getByNameAndSurname(String name, String surname);
}
