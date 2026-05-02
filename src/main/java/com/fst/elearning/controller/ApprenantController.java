package com.fst.elearning.controller;

import com.fst.elearning.entity.Apprenant;
import com.fst.elearning.entity.Cours;
import com.fst.elearning.service.ApprenantService;
import com.fst.elearning.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/apprenant")
public class ApprenantController {

    @Autowired
    private ApprenantService apprenantService;

    @Autowired
    private CoursService coursService;

    /**
     * Affiche le catalogue des cours (courses.html)
     * Injection de l'apprenant pour la barre de navigation personnalisée
     */
    @GetMapping("/home")
    public String afficherCatalogue(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Récupération de l'apprenant connecté via son email
        Apprenant apprenant = apprenantService.findByEmail(userDetails.getUsername());
        
        // Récupération de tous les cours pour le catalogue[cite: 1]
        List<Cours> courses = coursService.findAll();
        
        model.addAttribute("apprenant", apprenant);
        model.addAttribute("courses", courses);
        
        return "apprenant/courses"; 
    }

    /**
     * Affiche la page de modification du profil
     */
    @GetMapping("/profil")
    public String monProfil(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Apprenant apprenant = apprenantService.findByEmail(userDetails.getUsername());
        model.addAttribute("apprenant", apprenant);
        return "apprenant/profil";
    }

    /**
     * Traite la mise à jour des informations et de la photo de profil[cite: 1]
     */
    @PostMapping("/profil/update")
    public String updateProfil(@ModelAttribute Apprenant formDetails,
                               @RequestParam("imageFile") MultipartFile imageFile,
                               @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        
        Apprenant current = apprenantService.findByEmail(userDetails.getUsername());
        
        // Gestion de l'upload de l'image si un fichier est sélectionné[cite: 1]
        if (!imageFile.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
            Path path = Paths.get("src/main/resources/static/uploads/" + fileName);
            
            // Création du dossier s'il n'existe pas
            Files.createDirectories(path.getParent());
            Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            
            current.setPhoto(fileName);
        }

        // Mise à jour des champs textuels
        current.setNom(formDetails.getNom());
        current.setTelephone(formDetails.getTelephone());
        current.setDateNaissance(formDetails.getDateNaissance());
        
        apprenantService.save(current);
        
        // Redirection vers le profil avec un message de succès
        return "redirect:/apprenant/profil?success";
    }
}