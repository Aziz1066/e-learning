package com.fst.elearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/formateur/home")
    public String formateurHome() {
        return "formateur-home";
    }

    @GetMapping("/apprenant/home")
    public String apprenantHome() {
        return "apprenant-home";
    }
}