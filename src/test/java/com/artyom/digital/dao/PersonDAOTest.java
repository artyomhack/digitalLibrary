package com.artyom.digital.dao;

import com.artyom.digital.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonDAOTest {

    @Autowired
    private PersonDAO personDAO;

    @Test
    @Rollback
    public void insert() {
        String name = "Камилла Шакирова";
        String age = "19";
        Person p1 = new Person();
        p1.setFullName(name);
        p1.setAge(age);

        personDAO.save(p1);

        Assert.assertNotNull(p1.getId());
        Assert.assertEquals(name, p1.getFullName());
        Assert.assertEquals(age, p1.getAge());
    }

    @Test
    public void getPersonByBookIdAndPersonId() {
        var person = personDAO.fetchPersonByBookIdAndPersonId(12, 1);

        Assert.assertNotNull(person);
        Assert.assertEquals("Артём Хакимуллин", person.getFullName());
    }
}
