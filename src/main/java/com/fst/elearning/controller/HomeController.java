package com.fst.elearning.controller;

import com.fst.elearning.entity.Apprenant;
import com.fst.elearning.entity.Lecon;
import com.fst.elearning.service.ApprenantService; // Ajoutez cet import
import com.fst.elearning.service.CoursService;
import com.fst.elearning.service.LeconService;
import org.springframework.security.core.annotation.AuthenticationPrincipal; // Ajoutez cet import
import org.springframework.security.core.userdetails.UserDetails; // Ajoutez cet import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final CoursService coursService;
    private final LeconService leconService;
    private final ApprenantService apprenantService; // Ajoutez ceci

    // Mettez à jour le constructeur
    public HomeController(CoursService coursService, LeconService leconService, ApprenantService apprenantService) {
        this.coursService = coursService;
        this.leconService = leconService;
        this.apprenantService = apprenantService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/formateur/home")
    public String formateurHome() {
        return "formateur-home";
    }

    // CATALOGUE DES COURS - CORRIGÉ
    @GetMapping("/courses")
    public String courses(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        var list = coursService.findAll();
        model.addAttribute("courses", list);

        // On vérifie si l'utilisateur est connecté pour envoyer ses infos au template
        if (userDetails != null) {
            Apprenant apprenant = apprenantService.findByEmail(userDetails.getUsername());
            model.addAttribute("apprenant", apprenant);
        }
        
        return "courses";
    }

    @GetMapping("/course/{id}")
    public String courseDetails(@PathVariable Long id, Model model) {
        model.addAttribute("course", coursService.findByIdWithModules(id));
        return "course-details";
    }

    @GetMapping("/apprenant/lecon/{id}")
    public String showLesson(@PathVariable Long id, Model model) {
        Lecon lecon = leconService.findById(id);
        model.addAttribute("lecon", lecon);
        return "apprenant/lecon";
    }
}