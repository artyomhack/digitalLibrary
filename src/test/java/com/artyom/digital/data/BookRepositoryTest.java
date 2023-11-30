package com.artyom.digital.data;

import com.artyom.digital.data.book.Book;
import com.artyom.digital.domain.book.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testInsertBook() {
        // Создаем тестовую книгу
        Book book = Book.newOf("Test Book", "Test Author", "2023");
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setYear("2023");

        // Сохраняем книгу в репозитории
        Integer id = bookRepository.insert(book);

        // Проверяем, что книга была успешно сохранена
        Assert.assertNotNull(book);
        Assert.assertEquals("Test Book", book.getTitle());
        Assert.assertEquals("Test Author", book.getAuthor());
        Assert.assertEquals("2023", book.getYear());
        Assert.assertEquals(1, id.intValue());
    }
}
