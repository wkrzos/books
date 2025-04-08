package com.example.books.seeder;

import com.example.books.entity.User;
import com.example.books.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserSeeder {

    private final UserRepository userRepository;

    public void seedUsers() {

        if (userRepository.count() == 0) {
            log.info("Rozpoczynanie seedowania użytkowników.");

            List<User> users = List.of(
                    new User(1, "jdoe", "pass123", "jdoe@example.com", "John", "Doe", List.of()),
                    new User(2, "asmith", "password", "asmith@example.com", "Alice", "Smith", List.of()),
                    new User(3, "mkowalski", "qwerty", "mkowalski@example.com", "Marek", "Kowalski", List.of())
            );

            userRepository.saveAll(users);
            log.info("Użytkownicy zostali pomyślnie zapisani w bazie.");
        } else {
            log.info("Użytkownicy już istnieją w bazie, seedowanie zostało pominięte.");
        }
    }

}
