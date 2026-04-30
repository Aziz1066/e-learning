package com.fst.elearning.controller;

import com.fst.elearning.entity.Lecon;
import com.fst.elearning.service.CoursService;
import com.fst.elearning.service.LeconService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final CoursService coursService;
    private final LeconService leconService;

    public HomeController(CoursService coursService,
                          LeconService leconService) {
        this.coursService = coursService;
        this.leconService = leconService;
    }

    // ================= HOME =================
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // ================= FORMATEUR HOME =================
    @GetMapping("/formateur/home")
    public String formateurHome() {
        return "formateur-home";
    }

    // ================= ALL COURSES =================
    @GetMapping("/courses")
    public String courses(Model model) {

        var list = coursService.findAll();

        System.out.println("COURSES FROM DB = " + list.size());

        model.addAttribute("courses", list);

        return "courses";
    }

    // ================= APPRÉNANT HOME =================
    @GetMapping("/apprenant/home")
    public String apprenantHome() {
        return "redirect:/courses";
    }

    // ================= COURSE DETAILS =================
    @GetMapping("/course/{id}")
    public String courseDetails(@PathVariable Long id, Model model) {

        model.addAttribute("course",
                coursService.findByIdWithModules(id));

        return "course-details";
    }

    // ================= LESSON VIEW =================
    @GetMapping("/apprenant/lecon/{id}")
    public String showLesson(@PathVariable Long id, Model model) {

        Lecon lecon = leconService.findById(id);

        model.addAttribute("lecon", lecon);

        return "apprenant/lecon";
    }
}