package com.fst.elearning.service;

import com.fst.elearning.entity.Apprenant;
import com.fst.elearning.repository.ApprenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprenantService {

    @Autowired
    private ApprenantRepository apprenantRepository;

    public Apprenant findByEmail(String email) {
        return apprenantRepository.findByEmail(email);
    }

    public Apprenant save(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }

    public Apprenant findById(Long id) {
        return apprenantRepository.findById(id).orElse(null);
    }
}