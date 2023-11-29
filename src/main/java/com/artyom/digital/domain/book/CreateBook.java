package com.artyom.digital.domain.book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBook {
    private final String title;
    private final String author;
    private final String year;
}
