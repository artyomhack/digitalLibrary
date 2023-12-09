package com.artyom.digital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String getHello() {
        return "menu";
    }

    @GetMapping("/menu")
    public String getMenu() {
        return "menu";
    }
}
