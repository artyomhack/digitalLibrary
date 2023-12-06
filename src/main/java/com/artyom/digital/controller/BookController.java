package com.artyom.digital.controller;

import com.artyom.digital.DigitalApplication;
import com.artyom.digital.dao.BookDAO;
import com.artyom.digital.dao.PersonDAO;
import com.artyom.digital.model.Book;
import com.artyom.digital.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;


@Controller
@RequestMapping("/book")
public class BookController {

    private final Logger log = LoggerFactory.getLogger(DigitalApplication.class);

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("/create")
    public String showForm() {
        return "book/form";
    }

    @PostMapping("/create")
    public String createForm(@ModelAttribute("book") Book request) {
        bookDAO.save(request);
        return "redirect:/book/" + request.getId();
    }

    @GetMapping("/{id:[0-9]}")
    public ModelAndView info(@PathVariable("id") String id, @ModelAttribute("person") Person person) {
        var book = bookDAO.fetchById(Integer.valueOf(id));
        var model = getInfo(book);
        model.setViewName("book/info");
        return model;
    }
//
//    @PostMapping("/info/{id:[0-9]}/addPerson")
//    public String addSelectPerson(@PathVariable(name = "id") String id, Person person) {
//        bookDAO.addBookIdByPersonId(person.getId(), Integer.parseInt(id));
//        return "redirect:/book/info/" + id;
//    }

    private ModelAndView getInfo(Book book) {
        Objects.requireNonNull(book);
        var model = new ModelAndView();

        model.getModelMap().addAttribute("title", book.getTitle());
        model.getModelMap().addAttribute("author", book.getAuthor());
        model.getModelMap().addAttribute("year", book.getYear());
        model.getModelMap().addAttribute("person", book.getPerson());

        return model;
    }
}
