package com.fst.elearning.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "progression_lecon")
public class ProgressionLecon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apprenant_id", nullable = false)
    private Utilisateur apprenant;

    @ManyToOne
    @JoinColumn(name = "lecon_id", nullable = false)
    private Lecon lecon;

    private boolean completee = false;

    private LocalDateTime dateCompletion;

    // ================= GETTERS / SETTERS =================

    public Long getId() {
        return id;
    }

    public Utilisateur getApprenant() {
        return apprenant;
    }

    public void setApprenant(Utilisateur apprenant) {
        this.apprenant = apprenant;
    }

    public Lecon getLecon() {
        return lecon;
    }

    public void setLecon(Lecon lecon) {
        this.lecon = lecon;
    }

    public boolean isCompletee() {
        return completee;
    }

    public void setCompletee(boolean completee) {
        this.completee = completee;
    }

    public LocalDateTime getDateCompletion() {
        return dateCompletion;
    }

    public void setDateCompletion(LocalDateTime dateCompletion) {
        this.dateCompletion = dateCompletion;
    }
}