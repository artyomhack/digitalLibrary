package com.artyom.digital.data.book;

import com.artyom.digital.domain.book.BookRepository;
import com.artyom.digital.utils.GenerateId;
import com.artyom.digital.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(Book book) {
        Map<String, Object> params = new HashMap<>();

        var id = GenerateId.nextId();

        params.put("title", book.getTitle());
        params.put("author", book.getAuthor());
        params.put("year_", book.getYear());
        params.put("createdAt", book.getCreatedAt());

        String sql = SqlUtils.insert("book", "id", "title", "author", "year");

        jdbcTemplate.update(sql, params, id);

        return id;
    }

    @Override
    public List<Book> fetchAll() {
        return null;
    }

    @Override
    public Book fetchById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
