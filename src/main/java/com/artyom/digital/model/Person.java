package com.artyom.digital.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Person {
    private int id;
    private String fullName;
    private String age;

    public Person() {
    }

    public Person(int id, String fullName, String age) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
    }
}
