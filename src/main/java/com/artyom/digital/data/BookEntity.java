package com.artyom.digital.data;

import com.artyom.digital.domain.person.PersonItem;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Table("book")
@Data
public class BookEntity {
    @Id
    private final Integer id;
    private String title;
    private String author;
    @Column("year_")
    private String year;
    private List<PersonItem> persons;

    private LocalDateTime createdAt;

    public BookEntity(Integer id, String title, String author, String year, List<PersonItem> persons, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.persons = persons;
        this.createdAt = createdAt;
    }
}
