package com.artyom.digital.mapper;

import com.artyom.digital.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        var person = new Person();

        person.setId(rs.getInt("id"));
        person.setFullName(rs.getString("fullName"));
        person.setAge(rs.getString("age"));

        //Чтобы не писатьб это, можно использовать BeanPropertyRowMapper<>(Person.class)
        return person;
    }
}
