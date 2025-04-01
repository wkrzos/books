package com.example.books.service.generic;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CRUDService<T, ID> {
    T create(T dto);
    void delete(ID id);
    T update(ID id, T dto);
    T getById(ID id);
    List<T> getAll(Pageable pageable);
}
