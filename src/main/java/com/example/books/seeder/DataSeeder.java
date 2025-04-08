package com.example.books.seeder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final AuthorSeeder authorSeeder;
    private final BookSeeder bookSeeder;
    private final UserSeeder userSeeder;

    @Value("${seed.enabled:true}")
    private boolean seedEnabled;

    public DataSeeder(
            AuthorSeeder authorSeeder,
            BookSeeder bookSeeder,
            UserSeeder userSeeder
    ) {
        this.authorSeeder = authorSeeder;
        this.bookSeeder = bookSeeder;
        this.userSeeder = userSeeder;
    }

    @Override
    public void run(String... args) {
        if (!seedEnabled) {
            log.warn("⚠️  Seedowanie wyłączone (seed.enabled=false)");
            return;
        }

        log.info("🔁 Rozpoczynanie seedowania danych...");

        authorSeeder.seedAuthors();
        bookSeeder.seedBooks();
        userSeeder.seedUsers();

        log.info("✅ Seedowanie zakończone pomyślnie.");
    }

}
