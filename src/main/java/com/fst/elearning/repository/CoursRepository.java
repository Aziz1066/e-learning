package com.fst.elearning.repository;

import com.fst.elearning.entity.Cours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoursRepository extends JpaRepository<Cours, Long> {

    // Recherche par titre (keyword), catégorie et niveau (optionnels) 
    @Query("SELECT c FROM Cours c WHERE c.actif = true AND " +
           "(:kw IS NULL OR LOWER(c.titre) LIKE LOWER(CONCAT('%', :kw, '%'))) AND " +
           "(:cat IS NULL OR c.categorie = :cat)")
    Page<Cours> findPublicCourses(@Param("kw") String keyword, 
                                  @Param("cat") String categorie, 
                                  Pageable pageable);
    @Query("""
    		SELECT DISTINCT c FROM Cours c
    		LEFT JOIN FETCH c.modules
    		WHERE c.id = :id
    		""")
    		Cours findByIdWithModules(@Param("id") Long id);
}