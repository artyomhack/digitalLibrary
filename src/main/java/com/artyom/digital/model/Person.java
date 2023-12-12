package com.artyom.digital.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Person {
    private Integer id;
    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 35, message = "ФИО должно быть от 2 до 35 символов")
    private String fullName;
    @NotEmpty(message = "Возраст не должен быть пустым")
    @Min(value = 0, message = "Возраст должен быть больше 0")
    @Max(value = 135, message = "Возраст должен быть больше 0")
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
