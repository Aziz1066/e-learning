package com.fst.elearning.entity;

import jakarta.persistence.*;

@Entity
public class LessonProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long lessonId;

    private boolean completed = true;

    // getters setters
}