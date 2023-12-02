package com.artyom.digital.dao;

import com.artyom.digital.mapper.PersonMapper;
import com.artyom.digital.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
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
        String sql = "INSERT INTO Person(fullName, age) VALUES(?,?)";
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, person.getFullName());
            ps.setString(2, person.getAge());
            return ps;
        }, key);
    }

    public void updateById(int id, Person person) {
        String sql = "UPDATE Person SET fullName=? age=? WHERE id=?";
        jdbcTemplate.update(sql, person.getFullName(), person.getAge(), id);
    }

    public List<Person> fetchAll() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    public Person fetchById(int id) {
        String sql = "SELECT * FROM person WHERE id=?";
        return jdbcTemplate.query(sql, new PersonMapper(), id).stream()
                .findAny().orElse(null);
    }

    public void removeById(int id) {
        String sql = "DELETE FROM Person WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
