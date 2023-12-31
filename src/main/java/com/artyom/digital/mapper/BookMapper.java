package com.artyom.digital.mapper;

import com.artyom.digital.dao.PersonDAO;
import com.artyom.digital.model.Book;
import com.artyom.digital.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        var book = new Book();

        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getString("year_"));

        return book;
    }
}
