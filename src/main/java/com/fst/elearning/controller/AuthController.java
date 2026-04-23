package com.fst.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            model.addAttribute("error", "Cet email est déjà utilisé.");
            return "register";
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(passwordEncoder.encode(password));
        
        if ("FORMATEUR".equals(role)) {
            utilisateur.setRole(Role.FORMATEUR);
        } else {
            utilisateur.setRole(Role.APPRENANT);
        }
        
        utilisateurRepository.save(utilisateur);
        
        model.addAttribute("success", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
        return "login";
    }
    
    // AJOUTE CETTE MÉTHODE 👇
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}