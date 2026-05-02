package com.fst.elearning.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "module")
public class CoursModule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titre;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private Integer ordre;
    
    @ManyToOne
    @JoinColumn(name = "cours_id", nullable = false)
    private Cours cours;
    
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordre ASC")
    private List<Lecon> lecons = new ArrayList<>();

    // --- CONSTRUCTEURS ---
    public CoursModule() {}

    // --- GETTERS ET SETTERS (Obligatoires pour Thymeleaf) ---

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

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public List<Lecon> getLecons() {
        return lecons;
    }

    public void setLecons(List<Lecon> lecons) {
        this.lecons = lecons;
    }
}