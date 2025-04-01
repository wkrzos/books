package com.example.books.service.contract;

import com.example.books.dto.UserDTO;
import com.example.books.service.generic.CRUDService;

public interface UserService extends CRUDService<UserDTO, Integer> {
    UserDTO getByUsername(String username);
}
