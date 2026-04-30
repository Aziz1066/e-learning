package com.fst.elearning.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String categorie;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    private String imageUrl;

    private boolean actif = true;

    private LocalDateTime dateCreation = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "formateur_id", nullable = false)
    private Utilisateur formateur;

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordre ASC")
    private List<CoursModule> modules = new ArrayList<>();

    // --- CONSTRUCTEURS ---
    public Cours() {}

    public Cours(Long id, String titre, String description, String categorie, Niveau niveau, String imageUrl, boolean actif, LocalDateTime dateCreation, Utilisateur formateur) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.niveau = niveau;
        this.imageUrl = imageUrl;
        this.actif = actif;
        this.dateCreation = dateCreation;
        this.formateur = formateur;
    }

    // --- GETTERS ET SETTERS (Indispensables pour Thymeleaf) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Utilisateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Utilisateur formateur) {
        this.formateur = formateur;
    }

    public List<CoursModule> getModules() {
        return modules;
    }

    public void setModules(List<CoursModule> modules) {
        this.modules = modules;
    }
}