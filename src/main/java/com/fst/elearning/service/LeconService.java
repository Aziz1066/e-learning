package com.fst.elearning.service;

import com.fst.elearning.entity.Lecon;
import com.fst.elearning.repository.LeconRepository;
import org.springframework.stereotype.Service;

@Service
public class LeconService {

    private final LeconRepository leconRepository;

    public LeconService(LeconRepository leconRepository) {
        this.leconRepository = leconRepository;
    }

    public Lecon findById(Long id) {
        return leconRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }
}