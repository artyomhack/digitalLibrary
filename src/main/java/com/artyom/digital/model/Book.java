package com.artyom.digital.model;

import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String title;
    private String author;
    private String year;
    private Person person;

    public Book() {
    }

    public Book(Integer id, String title, String author, String year, Person person) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.person = person;
    }
}
