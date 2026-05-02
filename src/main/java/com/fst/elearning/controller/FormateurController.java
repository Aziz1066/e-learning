package com.fst.elearning.controller;

import com.fst.elearning.entity.*;
import com.fst.elearning.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

    private final CoursRepository coursRepository;
    private final ModuleRepository moduleRepository;
    private final LeconRepository leconRepository;

    // ROOT upload folder
    private final String UPLOAD_DIRECTORY =
            System.getProperty("user.dir") + "/uploads/";

    public FormateurController(CoursRepository coursRepository,
                               ModuleRepository moduleRepository,
                               LeconRepository leconRepository) {
        this.coursRepository = coursRepository;
        this.moduleRepository = moduleRepository;
        this.leconRepository = leconRepository;
    }

    // ======================== COURS ========================

    @GetMapping("/cours")
    public String listeMesCours(Model model) {
        model.addAttribute("listCourses", coursRepository.findAll());
        return "formateur/gestion-cours";
    }

    @GetMapping("/cours/nouveau")
    public String nouveauCours(Model model) {
        model.addAttribute("cours", new Cours());
        return "formateur/add-cours";
    }

    @PostMapping("/cours/save")
    public String enregistrerCours(@ModelAttribute Cours cours,
                                   @RequestParam("file") MultipartFile file) throws IOException {

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

    @GetMapping("/cours/delete/{id}")
    public String deleteCours(@PathVariable Long id) throws IOException {
        Cours cours = coursRepository.findById(id).orElseThrow();

        if (cours.getImageUrl() != null) {
            Path path = Paths.get(System.getProperty("user.dir") + cours.getImageUrl());
            Files.deleteIfExists(path);
        }

        coursRepository.delete(cours);

        return "redirect:/formateur/cours";
    }

    // ======================== MODULES ========================

    @GetMapping("/cours/{coursId}/modules")
    public String gestionModules(@PathVariable Long coursId, Model model) {
        Cours cours = coursRepository.findById(coursId).orElseThrow();

        model.addAttribute("cours", cours);
        model.addAttribute("modules",
                moduleRepository.findByCoursIdOrderByOrdreAsc(coursId));
        model.addAttribute("newModule", new CoursModule());

        return "formateur/modules";
    }

    @PostMapping("/cours/{coursId}/modules/save")
    public String enregistrerModule(@PathVariable Long coursId,
                                    @ModelAttribute CoursModule coursModule) {

        coursModule.setCours(coursRepository.findById(coursId).orElseThrow());
        moduleRepository.save(coursModule);

        return "redirect:/formateur/cours/" + coursId + "/modules";
    }

    @GetMapping("/modules/delete/{id}")
    public String deleteModule(@PathVariable Long id) {
        CoursModule module = moduleRepository.findById(id).orElseThrow();
        Long coursId = module.getCours().getId();

        moduleRepository.delete(module);

        return "redirect:/formateur/cours/" + coursId + "/modules";
    }

    // ======================== LEÇONS ========================

    @GetMapping("/modules/{moduleId}/lecons")
    public String gestionLecons(@PathVariable Long moduleId, Model model) {
        CoursModule module = moduleRepository.findById(moduleId).orElseThrow();

        model.addAttribute("module", module);
        model.addAttribute("lecons",
                leconRepository.findByModuleIdOrderByOrdreAsc(moduleId));
        model.addAttribute("newLecon", new Lecon());

        return "formateur/lecons";
    }

    @PostMapping("/modules/{moduleId}/lecons/save")
    public String enregistrerLecon(@PathVariable Long moduleId,
                                   @ModelAttribute Lecon lecon,
                                   @RequestParam("file") MultipartFile file) throws IOException {

        // set module first
        lecon.setModule(moduleRepository.findById(moduleId).orElseThrow());

        // upload PDF
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