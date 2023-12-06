package com.artyom.digital.controller;

import com.artyom.digital.dao.BookDAO;
import com.artyom.digital.dao.PersonDAO;
import com.artyom.digital.model.Book;
import com.artyom.digital.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;


@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "book/form";
    }

    @PostMapping("/create")
    public String createForm(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return "redirect:/book/info/" + book.getId();
    }

    @GetMapping("/info/{id}")
    public ModelAndView getInfo(@PathVariable(name = "id") String id, @ModelAttribute("person") Person person) {
        var book = bookDAO.fetchById(Integer.valueOf(id));
        var modelAndView = mapToInfo(book);
        modelAndView.getModelMap().addAttribute("persons", personDAO.fetchAll());
        modelAndView.setViewName("book/info");
        return modelAndView;
    }

    @PostMapping("/info/{id}/addPerson")
    public String addPerson(@PathVariable(name = "id") String id, @ModelAttribute("person") Person person) {
        bookDAO.addBookIdByPersonId(person.getId(), Integer.valueOf(id));
        return "redirect:/book/info/" + id;
    }


    private ModelAndView mapToInfo(Book book) {
        Objects.requireNonNull(book);
        var model = new ModelAndView();

        model.getModelMap().addAttribute("bookId", book.getId());
        model.getModelMap().addAttribute("title", book.getTitle());
        model.getModelMap().addAttribute("author", book.getAuthor());
        model.getModelMap().addAttribute("year", book.getYear());

        return model;
    }

}
