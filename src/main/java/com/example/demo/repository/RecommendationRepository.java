package com.example.demo.repository;

import com.example.demo.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecommendationRepository
        extends JpaRepository<Recommendation, Long> {

    // ✅ Used in t42_recommendation_ids_csv
    Recommendation findByUserIdOrderByGeneratedAtDesc(Long userId);

    // ✅ Used in HQL / range tests (t58, t59)
    List<Recommendation> findByUserIdAndGeneratedAtBetween(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );
}
