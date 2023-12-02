package com.artyom.digital.model;

import lombok.Data;
import java.util.List;

@Data
public class Book {
    private final Integer id;
    private String title;
    private String author;
    private String year;
    private List<Person> persons;

    public Book(Integer id, String title, String author, String year, List<Person> persons) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.persons = persons;
    }
}
