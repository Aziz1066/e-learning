package com.fst.elearning.controller;

import com.fst.elearning.entity.*;
import com.fst.elearning.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

    private final CoursRepository coursRepository;
    private final ModuleRepository moduleRepository;
    private final LeconRepository leconRepository;
    private final UtilisateurRepository utilisateurRepository;

    private final String UPLOAD_DIRECTORY =
            System.getProperty("user.dir") + "/uploads/";

    public FormateurController(CoursRepository coursRepository,
                               ModuleRepository moduleRepository,
                               LeconRepository leconRepository,
                               UtilisateurRepository utilisateurRepository) {

        this.coursRepository = coursRepository;
        this.moduleRepository = moduleRepository;
        this.leconRepository = leconRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    // ================= COURS =================

    @GetMapping("/cours")
    public String listeCours(Model model) {
        model.addAttribute("listCourses", coursRepository.findAll());
        return "formateur/gestion-cours";
    }

    @GetMapping("/cours/nouveau")
    public String nouveauCours(Model model) {
        model.addAttribute("cours", new Cours());
        model.addAttribute("niveaux", Niveau.values());
        return "formateur/add-cours";
    }

    @PostMapping("/cours/save")
    public String enregistrerCours(@ModelAttribute Cours cours,
                                   @RequestParam("file") MultipartFile file,
                                   Principal principal) throws IOException {

        // GET logged user
        Utilisateur formateur = utilisateurRepository
                .findByEmail(principal.getName())
                .orElseThrow();

        cours.setFormateur(formateur);

        // upload image
        if (!file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, file.getBytes());

            cours.setImageUrl("/uploads/" + fileName);
        }

        cours.setActif(true);
        coursRepository.save(cours);

        return "redirect:/formateur/cours";
    }

    // ================= MODULES =================

    @GetMapping("/cours/{coursId}/modules")
    public String modules(@PathVariable Long coursId, Model model) {

        Cours cours = coursRepository.findById(coursId).orElseThrow();

        model.addAttribute("cours", cours);
        model.addAttribute("modules",
                moduleRepository.findByCoursIdOrderByOrdreAsc(coursId));
        model.addAttribute("newModule", new CoursModule());

        return "formateur/modules";
    }

    @PostMapping("/cours/{coursId}/modules/save")
    public String saveModule(@PathVariable Long coursId,
                             @ModelAttribute CoursModule module) {

        module.setCours(coursRepository.findById(coursId).orElseThrow());
        moduleRepository.save(module);

        return "redirect:/formateur/cours/" + coursId + "/modules";
    }

    // ================= LEÇONS =================

    @GetMapping("/modules/{moduleId}/lecons")
    public String lecons(@PathVariable Long moduleId, Model model) {

        CoursModule module = moduleRepository.findById(moduleId).orElseThrow();

        List<Lecon> lecons =
                leconRepository.findByModuleIdOrderByOrdreAsc(moduleId);

        model.addAttribute("module", module);
        model.addAttribute("lecons", lecons);
        model.addAttribute("newLecon", new Lecon());

        return "formateur/lecons";
    }

    @PostMapping("/modules/{moduleId}/lecons/save")
    public String saveLecon(@PathVariable Long moduleId,
                            @ModelAttribute Lecon lecon,
                            @RequestParam("file") MultipartFile file) throws IOException {

        lecon.setModule(moduleRepository.findById(moduleId).orElseThrow());

        if (!file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path uploadPath = Paths.get(UPLOAD_DIRECTORY + "lecons/");
            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, file.getBytes());

            lecon.setPdfUrl("/uploads/lecons/" + fileName);
        }

        leconRepository.save(lecon);

        return "redirect:/formateur/modules/" + moduleId + "/lecons";
    }

    @GetMapping("/lecons/delete/{id}")
    public String deleteLecon(@PathVariable Long id) throws IOException {

        Lecon lecon = leconRepository.findById(id).orElseThrow();
        Long moduleId = lecon.getModule().getId();

        if (lecon.getPdfUrl() != null) {
            Path path = Paths.get(System.getProperty("user.dir") + lecon.getPdfUrl());
            Files.deleteIfExists(path);
        }

        leconRepository.delete(lecon);

        return "redirect:/formateur/modules/" + moduleId + "/lecons";
    }
}