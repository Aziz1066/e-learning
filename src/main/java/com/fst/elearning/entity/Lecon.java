package com.fst.elearning.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lecon")
public class Lecon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    private Integer ordre;
    private Integer dureeMin;

    // ✅ NEW FIELD: PDF FILE PATH
    private String pdfUrl;
    

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private CoursModule module;

    public Lecon() {}

    // ================= GETTERS / SETTERS =================

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

    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Integer getOrdre() {
        return ordre;
    }
    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public Integer getDureeMin() {
        return dureeMin;
    }
    public void setDureeMin(Integer dureeMin) {
        this.dureeMin = dureeMin;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }
    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public CoursModule getModule() {
        return module;
    }
    public void setModule(CoursModule module) {
        this.module = module;
    }
}