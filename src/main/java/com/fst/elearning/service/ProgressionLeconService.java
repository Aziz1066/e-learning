package com.fst.elearning.service;

import com.fst.elearning.entity.*;
import com.fst.elearning.repository.ProgressionLeconRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProgressionLeconService {

    private final ProgressionLeconRepository repo;

    public ProgressionLeconService(ProgressionLeconRepository repo) {
        this.repo = repo;
    }

    public void markAsCompleted(Utilisateur user, Lecon lecon) {

        ProgressionLecon progression =
                repo.findByApprenantAndLecon(user, lecon)
                        .orElse(new ProgressionLecon());

        progression.setApprenant(user);
        progression.setLecon(lecon);
        progression.setCompletee(true);
        progression.setDateCompletion(LocalDateTime.now());

        repo.save(progression);
    }

    // ✅ ADD THIS (YOU WERE MISSING IT)
    public boolean isCompleted(Utilisateur user, Lecon lecon) {
        return repo.existsByApprenantAndLeconAndCompleteeTrue(user, lecon);
    }
}