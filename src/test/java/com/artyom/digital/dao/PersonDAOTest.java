package com.artyom.digital.dao;

import com.artyom.digital.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonDAOTest {

    @Autowired
    private PersonDAO personDAO;

    @Test
    public void insert() {
        Person p1 = new Person();
        p1.setFullName("Артём Хакимуллин");
        p1.setAge("20");
        personDAO.save(p1);

        Assert.assertEquals("Артём Хакимуллин", p1.getFullName());
        Assert.assertEquals("20", p1.getAge());
    }

}
