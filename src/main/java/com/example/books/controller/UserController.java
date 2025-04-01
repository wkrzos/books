package com.example.books.controller;

import com.example.books.dto.UserDTO;
import com.example.books.service.contract.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAll();

        return ResponseEntity
                .ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        UserDTO user = userService.getById(id);

        return ResponseEntity
                .ok(user);
    }

    @GetMapping("/find")
    public ResponseEntity<UserDTO> getUserByUsername(@RequestParam String username) {
        UserDTO user = userService.getByUsername(username);

        return ResponseEntity
                .ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        UserDTO createdUser = userService.create(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO user) {
        UserDTO updatedUser = userService.update(id, user);

        return ResponseEntity
                .ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
