package com.artyom.digital.dao;

import com.artyom.digital.mapper.PersonMapper;
import com.artyom.digital.model.Book;
import com.artyom.digital.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Person person) {
        String sql = "INSERT INTO person(fullName, age) VALUES(?,?)";
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, person.getFullName());
            ps.setString(2, person.getAge());
            return ps;
        }, key);
        person.setId(Objects.requireNonNull(key.getKey()).intValue());
    }

    public void update(Integer id, Person person) {
        String sql = "UPDATE Person SET fullName=?, age=? WHERE id=?";
        jdbcTemplate.update(sql, person.getFullName(), person.getAge(), id);
    }

    public List<Person> fetchAll() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    public Person fetchById(int id) {
        String sql = "SELECT * FROM person WHERE id=?";
        return jdbcTemplate.query(sql, new PersonMapper(), id)
                .stream().findAny()
                .orElseThrow(() -> new IllegalArgumentException("PersonId not found"));
    }

//    public List<Book> fetchBooksByPersonId(Integer person_id) {
//        String sql = "SELECT * FROM book WHERE person_id=?";
//        return jdbcTemplate.query(sql, new Object[]{person_id}, new BeanPropertyRowMapper<>(Book.class));
//    }

    public Person fetchPersonFromBookIdByPersonId(Integer book_id, Integer person_id) {
        String sql = "SELECT pers.* FROM person JOIN
    }

    public void removeById(int id) {
        String sql = "DELETE FROM Person WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
