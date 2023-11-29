package com.artyom.digital.domain.book;

import com.artyom.digital.domain.person.PersonItem;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
public class Book {
    private final Integer id;
    private String title;
    private String author;
    private String year;
    private List<PersonItem> persons;

    private LocalDateTime createdAt;

    public static Book newOf(String title, String author, String year, List<PersonItem> persons) {
        return of(null, title, author, year, persons, null);
    }

    public static Book of(Integer id, String title, String author, String year, List<PersonItem> persons, LocalDateTime createdAt) {
        return new Book(id, title, author, year, persons, createdAt);
    }

    public Book(Integer id, String title, String author, String year, List<PersonItem> persons, LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id);
        this.title = title;
        this.author = author;
        this.year = year;
        this.persons = persons;
        this.createdAt = Optional.of(Objects.requireNonNull(createdAt))
                .orElse(LocalDateTime.now());
    }
}
