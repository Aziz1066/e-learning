package com.fst.elearning.repository;

import com.fst.elearning.entity.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

    Apprenant findByEmail(String email);

}