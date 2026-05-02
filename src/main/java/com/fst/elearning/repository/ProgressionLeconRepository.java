package com.fst.elearning.repository;

import com.fst.elearning.entity.ProgressionLecon;
import com.fst.elearning.entity.Lecon;
import com.fst.elearning.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressionLeconRepository extends JpaRepository<ProgressionLecon, Long> {

    boolean existsByApprenantAndLecon(Utilisateur apprenant, Lecon lecon);

    Optional<ProgressionLecon> findByApprenantAndLecon(Utilisateur apprenant, Lecon lecon);

    long countByApprenantIdAndLeconIdInAndCompleteeTrue(Long userId, List<Long> lessonIds);

    List<ProgressionLecon> findByApprenantIdAndLeconIdInAndCompleteeTrue(Long userId, List<Long> lessonIds);
}