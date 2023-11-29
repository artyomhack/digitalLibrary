package com.artyom.digital.domain.book;

import java.util.List;

public interface BookService {
    Integer create(CreateBook book);

    void update(Integer id, CreateBook book);

    List<BookItem> fetchAll();

    BookDescription fetchById();

    void removeById(Integer id);
}
