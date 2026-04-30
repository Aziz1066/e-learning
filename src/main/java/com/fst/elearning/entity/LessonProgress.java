package com.fst.elearning.entity;

import jakarta.persistence.*;

@Entity

public class LessonProgress {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Lecon lecon;

    private boolean completed = true;
}