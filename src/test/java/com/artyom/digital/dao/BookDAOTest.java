package com.artyom.digital.dao;

import com.artyom.digital.model.Book;
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
public class BookDAOTest {

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private PersonDAO personDAO;

    @Test
    @Rollback
    public void insert() {
        Book book = new Book();
        book.setTitle("Капитанская дочка");
        book.setAuthor("А.С Пушкин");
        book.setYear("1985");

        bookDAO.save(book);

        Assert.assertNotNull(book.getId());
        Assert.assertEquals("Капитанская дочка", book.getTitle());
        Assert.assertEquals("А.С Пушкин", book.getAuthor());
        Assert.assertEquals("1985", book.getYear());
        Assert.assertNull(book.getPerson());
    }
}
