package com.artyom.digital.controller;

import com.artyom.digital.dao.PersonDAO;
import com.artyom.digital.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonDAO personDAO;

    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/create")
    public String showForm(@ModelAttribute("person") Person person) {
        return "person/form";
    }

    @PostMapping("/create")
    public String createForm(@ModelAttribute("person") Person request) {
        personDAO.save(request);
        return "redirect:/person/info/" + request.getId();
    }

    @GetMapping("/info/{id}")
    public ModelAndView index(@PathVariable("id") String id) {
        var person = personDAO.fetchById(Integer.parseInt(id));
        var model = getInfo(person);
        model.setViewName("/person/index");
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
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") String id) {
        personDAO.update(Integer.valueOf(id), person);
        return "redirect:/person/info/" + id;
    }

    public ModelAndView getInfo(Person person) {
        Objects.requireNonNull(person);
        var model = new ModelAndView();

        model.getModelMap().addAttribute("id", person.getId());
        model.getModelMap().addAttribute("fullName", person.getFullName());
        model.getModelMap().addAttribute("age", person.getAge());
        model.getModelMap().addAttribute("books", person.getBooks());

        return model;
    }
}
