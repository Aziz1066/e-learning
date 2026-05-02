package com.fst.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fst.elearning.entity.Role;
import com.fst.elearning.entity.Utilisateur;
import com.fst.elearning.repository.UtilisateurRepository;

@Controller
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String nom,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {

        if (utilisateurRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email déjà utilisé");
            return "register";
        }

        Utilisateur u = new Utilisateur();
        u.setNom(nom);
        u.setEmail(email);
        u.setMotDePasse(passwordEncoder.encode(password));

        if ("FORMATEUR".equals(role)) {
            u.setRole(Role.FORMATEUR);
        } else {
            u.setRole(Role.APPRENANT);
        }

        utilisateurRepository.save(u);

        model.addAttribute("success", "Inscription réussie");
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}