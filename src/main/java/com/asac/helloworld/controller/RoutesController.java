package com.asac.helloworld.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@RequestMapping("/")
public class RoutesController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "world") String name,
                            Model model) {
        model.addAttribute("name", name); // this is passed to the template automatically
        return "hello"; // this represents the name of the html template which will be rendered
    }
    @GetMapping("/capitalize/{name}")
    @ResponseBody
    public String capitalize(
            @PathVariable String name) {
        return name.toUpperCase();
    }
}
