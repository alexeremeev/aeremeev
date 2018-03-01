package ru.job4j.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {

    @GetMapping(value = "/")
    public String main() {
        return "index";
    }

    @GetMapping(value = "/create")
    public String createNewOrder() {
        return "create";
    }
}
