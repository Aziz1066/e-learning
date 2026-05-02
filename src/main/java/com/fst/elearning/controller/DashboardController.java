package com.fst.elearning.controller;

import com.fst.elearning.entity.*;
import com.fst.elearning.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class DashboardController {

    private final UtilisateurRepository utilisateurRepository;
    private final InscriptionRepository inscriptionRepository;

    public DashboardController(UtilisateurRepository utilisateurRepository,
                               InscriptionRepository inscriptionRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.inscriptionRepository = inscriptionRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        System.out.println("LOGIN USER = " + principal.getName());

        Utilisateur user = utilisateurRepository
                .findByEmail(principal.getName())
                .orElseThrow();

        System.out.println("USER ID = " + user.getId());

        List<Inscription> inscriptions =
                inscriptionRepository.findByApprenantId(user.getId());

        System.out.println("INSCRIPTIONS SIZE = " + inscriptions.size());

        model.addAttribute("inscriptions", inscriptions);

        return "dashboard";
    }
}