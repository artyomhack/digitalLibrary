package com.artyom.digital.mapper;

import com.artyom.digital.model.Book;
import com.artyom.digital.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookPersonMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getString("year_"));

        Person person = new Person();
        person.setId(rs.getInt("person_id"));
        person.setFullName(rs.getString("fullName"));
        person.setAge(rs.getString("age"));

        book.setPerson(person);
        return book;
    }
}
