package com.artyom.digital.domain.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Integer create(CreateBook book);

    void update(Integer id, CreateBook book);

    List<BookItem> fetchAll();

    BookDescription fetchById();

    void removeById(Integer id);
}
