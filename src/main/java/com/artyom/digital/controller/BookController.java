package com.artyom.digital.controller;

import com.artyom.digital.dao.BookDAO;
import com.artyom.digital.dao.PersonDAO;
import com.artyom.digital.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String showCreateForm(@ModelAttribute("book") Book book,
                                 Model model) {
        model.addAttribute("persons", personDAO.fetchAll());
        return "book/form";
    }

    @PostMapping("/create")
    public String createForm(@ModelAttribute("book") Book book,
                             @RequestParam(name = "personId", required = false) String personId)
    {
        bookDAO.ByBookIdAddPersonId(book.getId(), Integer.parseInt(personId));
        return "redirect:/book/info/" + book.getId();
    }

    @GetMapping("/info/{bookId}")
    public ModelAndView getInfoWithPersonId(@PathVariable("bookId") String bookId) {
        var book = bookDAO.fetchById(Integer.valueOf(bookId));
        var person = book.getPerson();
        var model = mapToInfo(book);
        model.getModelMap().addAttribute("person", person);
        model.setViewName("book/info");
        return model;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("books", bookDAO.fetchAllFromBook());
        return "book/list";
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
