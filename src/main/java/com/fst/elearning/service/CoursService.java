package com.fst.elearning.service;

import com.fst.elearning.entity.Cours;
import com.fst.elearning.repository.CoursRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursService {

    private final CoursRepository coursRepository;

    // Constructor injection
    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }
    public Cours findByIdWithModules(Long id) {
        return coursRepository.findByIdWithModules(id);
    }

    /**
     * 📚 Get all courses (simple list)
     */
    public List<Cours> findAll() {
        return coursRepository.findAll();
    }

    /**
     * 🔍 Get course by ID
     */
    public Cours findById(Long id) {
        return coursRepository.findById(id)
                .orElse(null);
    }

    /**
     * 📖 Catalogue with pagination + search/filter
     */
    public Page<Cours> getCatalogue(String keyword, String category, int page, int size) {
        return coursRepository.findPublicCourses(
                keyword,
                category,
                PageRequest.of(page, size)
        );
    }
}