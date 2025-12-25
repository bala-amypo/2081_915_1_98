package com.example.demo.repository;

import com.example.demo.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationRepository
        extends JpaRepository<Recommendation, Long> {

    Optional<Recommendation> findFirstByUserIdOrderByGeneratedAtDesc(Long userId);
}
