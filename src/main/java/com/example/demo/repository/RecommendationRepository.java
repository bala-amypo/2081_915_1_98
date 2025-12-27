package com.example.demo.repository;

import com.example.demo.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    // ✅ Required by t59_latest_recommendation_failure
    List<Recommendation> findByUserIdOrderByGeneratedAtDesc(Long userId);

    // ✅ Required by range test
    List<Recommendation> findByUserIdAndGeneratedAtBetween(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );
}
