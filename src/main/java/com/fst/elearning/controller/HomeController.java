package com.fst.elearning.controller;

import com.fst.elearning.entity.*;
import com.fst.elearning.repository.UtilisateurRepository;
import com.fst.elearning.service.CoursService;
import com.fst.elearning.service.LeconService;
import com.fst.elearning.service.ProgressionLeconService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final CoursService coursService;
    private final LeconService leconService;
    private final ProgressionLeconService progressionService;
    private final UtilisateurRepository utilisateurRepository;

    public HomeController(CoursService coursService,
                          LeconService leconService,
                          ProgressionLeconService progressionService,
                          UtilisateurRepository utilisateurRepository) {

        this.coursService = coursService;
        this.leconService = leconService;
        this.progressionService = progressionService;
        this.utilisateurRepository = utilisateurRepository;
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

    // ================= COURSES =================
    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", coursService.findAll());
        return "courses";
    }

    // ================= COURSE DETAILS (PROGRESS FIXED) =================
    @GetMapping("/course/{id}")
    public String courseDetails(@PathVariable Long id,
                                Model model,
                                Principal principal) {

        Cours course = coursService.findById(id);

        Utilisateur user = utilisateurRepository
                .findByEmail(principal.getName())
                .orElseThrow();

        List<Lecon> lecons = course.getModules()
                .stream()
                .flatMap(m -> m.getLecons().stream())
                .toList();

        long total = lecons.size();

        long done = lecons.stream()
                .filter(l -> progressionService.isCompleted(user, l))
                .count();

        int percent = total == 0 ? 0 : (int)((done * 100) / total);

        List<Long> completedLessons = lecons.stream()
                .filter(l -> progressionService.isCompleted(user, l))
                .map(Lecon::getId)
                .toList();

        model.addAttribute("course", course);
        model.addAttribute("progressPercent", percent);
        model.addAttribute("completedLessons", completedLessons);

        return "course-details";
    }

    // ================= OPEN LESSON =================
    @GetMapping("/apprenant/lecon/{id}")
    public String openLesson(@PathVariable Long id, Model model) {

        Lecon lecon = leconService.findById(id);

        model.addAttribute("lecon", lecon);

        return "lesson-page";
    }
    // ================= COMPLETE LESSON =================
    @GetMapping("/lecon/{id}/complete")
    public String completeLesson(@PathVariable Long id, Principal principal) {

        Lecon lecon = leconService.findById(id);

        Utilisateur user = utilisateurRepository
                .findByEmail(principal.getName())
                .orElseThrow();

        progressionService.markAsCompleted(user, lecon);

        return "redirect:/course/" + lecon.getModule().getCours().getId();
    }
}