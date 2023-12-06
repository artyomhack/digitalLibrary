package com.artyom.digital.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {
    private Integer id;
    private String fullName;
    private String age;
    private List<Book> books;

    public Person() {
    }

    public Person(int id, String fullName, String age, List<Book> books) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.books = books;
    }
}
