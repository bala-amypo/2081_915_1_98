package com.example.demo.repository;

import com.example.demo.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    // 🔹 Latest recommendation for a user
    Optional<Recommendation> findTopByUserIdOrderByGeneratedAtDesc(Long userId);

    // 🔹 Recommendations for a user within a time range
    List<Recommendation> findByUserIdAndGeneratedAtBetween(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );
}
