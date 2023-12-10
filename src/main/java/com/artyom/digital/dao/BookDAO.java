package com.artyom.digital.dao;

import com.artyom.digital.mapper.BookMapper;
import com.artyom.digital.mapper.BookPersonMapper;
import com.artyom.digital.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
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
        String sql = "INSERT INTO book(title, author, year_, person_id) VALUES(?, ?, ?, ?)";
        var key = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getYear());
            ps.setInt(4, book.getPerson().getId());
            return ps;
        }, key);

        book.setId(Objects.requireNonNull(key.getKey()).intValue());
    }

    public Book fetchById(Integer bookId) {
        String sql = "SELECT book.*, person.* FROM book LEFT JOIN person ON book.person_id = person.id WHERE book.id = ?";
        return jdbcTemplate.queryForObject(sql, new BookPersonMapper(), bookId);
    }

    public List<Book> fetchAll() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    public List<Book> fetchAllFromBook() {
        String sql = "SELECT book.*, person.* FROM book LEFT JOIN person ON book.person_id = person.id";

        return jdbcTemplate.query(sql, new BookPersonMapper());
    }

    public void addByBookIdPersonId(Integer bookId, Integer personId) {
        String sql = "UPDATE book SET title=?, author=?, year_=?, person_id=? WHERE id=?";
        var book = fetchById(bookId);
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(),
                            book.getYear(), personId, bookId);
    }
}
