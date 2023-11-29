package com.artyom.digital.domain.person;

import com.artyom.digital.domain.book.BookItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
public class Person {
    private final Integer id;
    private String fullName;
    private String age;
    private final LocalDateTime createdAt;

    private final List<BookItem> books;

    public static Person of(String fullName, String age, List<BookItem> books) {
        return of(null, fullName, age, null, books);
    }

    public static Person of(Integer id, String fullName, String age, LocalDateTime createdAt, List<BookItem> books) {
        return new Person(id, fullName, age, createdAt, books);
    }

    public Person(Integer id, String fullName, String age, LocalDateTime createdAt, List<BookItem> books) {
        this.id = Objects.requireNonNull(id);
        this.fullName = fullName;
        this.age = age;
        this.createdAt = Optional.of(Objects.requireNonNull(createdAt)).orElse(LocalDateTime.now());
        this.books = books;
    }
}
