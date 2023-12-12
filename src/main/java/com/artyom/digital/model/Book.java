package com.artyom.digital.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class Book {
    private Integer id;
    @NotEmpty(message = "Должно быть название книги")
    private String title;
    @NotEmpty(message = "Должно быть название автора книги")
    private String author;
    @NotEmpty(message = "Должен быть год создания книги")
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
