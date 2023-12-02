package com.artyom.digital.model;

import lombok.Data;

@Data
public class Person {
    private Integer id;
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
