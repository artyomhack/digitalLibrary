package com.artyom.digital.data.book;

import com.artyom.digital.domain.person.PersonItem;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Table(schema = "book")
@Data
public class Book {
    @Id
    private final Integer id;
    private String title;
    private String author;
    @Column("year_")
    private String year;
    private List<PersonItem> persons;

    private LocalDateTime createdAt;

    public static Book newOf(String title, String author, String year) {
        return of(null, title, author, year, new ArrayList<>(), null);
    }

    public static Book of(Integer id, String title, String author, String year, List<PersonItem> persons, LocalDateTime createdAt) {
        return new Book(id, title, author, year, persons, createdAt);
    }

    public Book(Integer id, String title, String author, String year, List<PersonItem> persons, LocalDateTime createdAt) {
        this.id = Objects.isNull(id) ? 0 : id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.persons = persons;
        this.createdAt = Objects.isNull(createdAt) ? LocalDateTime.now() : createdAt;
    }
}
