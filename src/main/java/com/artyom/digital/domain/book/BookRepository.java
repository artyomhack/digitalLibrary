package com.artyom.digital.domain.book;

import com.artyom.digital.data.book.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookRepository {
    Integer insert(Book book);

    List<Book> fetchAll();

    Book fetchById(Integer id);

    void deleteById(Integer id);
}
