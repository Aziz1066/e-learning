package com.fst.elearning.repository;

import com.fst.elearning.entity.CoursModule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ModuleRepository extends JpaRepository<CoursModule, Long> {
    // Trouver les modules d'un cours spécifique triés par ordre
    List<CoursModule> findByCoursIdOrderByOrdreAsc(Long coursId);
}