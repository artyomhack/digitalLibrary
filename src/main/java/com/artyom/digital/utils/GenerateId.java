package com.artyom.digital.utils;

import java.util.Objects;

public class GenerateId {
    private final Integer id;
    private static Integer counterId = 0;

    public GenerateId(Integer id) {
        this.id = id;
    }

    public static Integer nextId() {
        return counterId+=1;
    }

    public Integer getId() {
        return id;
    }
}
