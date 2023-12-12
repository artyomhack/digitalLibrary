package com.artyom.digital.controller;

import com.artyom.digital.dao.BookDAO;
import com.artyom.digital.dao.PersonDAO;
import com.artyom.digital.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("person", new Person());
        return "person/form";
    }

    @PostMapping("/create")
    public String createForm(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "person/form";

        personDAO.save(person);
        return "redirect:/person/info/" + person.getId();
    }

    @GetMapping("/info/{id}")
    public ModelAndView showInfo(@PathVariable("id") String id) {
        var person = personDAO.fetchById(Integer.parseInt(id));
        var model = getInfo(person);
        model.getModelMap().addAttribute("books", bookDAO.fetchAllByPersonId(Integer.valueOf(id)));
        model.setViewName("person/info");
        return model;
    }

    @GetMapping("/list")
    public String showList(Model model) {
        //Get all Person from DAO
        var persons = personDAO.fetchAll();
        model.addAttribute("persons", persons);
        return "/person/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("person", personDAO.fetchById(Integer.parseInt(id)));
        return "person/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") String id) {

        if (bindingResult.hasErrors())
            return "person/edit";

        personDAO.update(Integer.valueOf(id), person);
        return "redirect:/person/info/" + id;
    }

    @DeleteMapping("/{id}/remove")
    public String delete(@PathVariable("id") String id) {
        personDAO.removeById(Integer.parseInt(id));

        return "redirect:/person/list";
    }

    public ModelAndView getInfo(Person person) {
        Objects.requireNonNull(person);
        var model = new ModelAndView();

        model.getModelMap().addAttribute("id", person.getId());
        model.getModelMap().addAttribute("fullName", person.getFullName());
        model.getModelMap().addAttribute("age", person.getAge());

        return model;
    }
}
