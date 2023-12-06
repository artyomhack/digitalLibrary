package com.artyom.digital.dao;

import com.artyom.digital.mapper.BookMapper;
import com.artyom.digital.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Book book) {
        String sql = "INSERT INTO book(title, author, year_) VALUES(?, ?, ?)";
        var key = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getYear());
            return ps;
        }, key);

        book.setId(Objects.requireNonNull(key.getKey()).intValue());
    }

    public Book fetchById(Integer bookId) {
        String sql = "SELECT * FROM book WHERE id=?";

        return jdbcTemplate.query(sql, new BookMapper(), bookId)
                .stream().findAny()
                .orElseThrow(() -> new IllegalArgumentException("BookId not found"));
    }

    public List<Book> fetchAll() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    public void addBookIdByPersonId(Integer person_id, Integer book_id) {
        String sql = "UPDATE book SET title=?, author=?, year_=?, person_id=? WHERE id=?";
        var book = fetchById(book_id);
        jdbcTemplate.update(sql, book.getId(), book.getTitle(), book.getAuthor(),
                            book.getYear(), person_id, book_id);

    }
}
