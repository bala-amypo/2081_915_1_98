package com.example.demo.repository;

import com.example.demo.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    // FIXED METHOD
    @Query("""
        SELECT r FROM Recommendation r
        WHERE r.userId = :userId
        ORDER BY r.generatedAt DESC
    """)
    List<Recommendation> findByUserIdOrderByGeneratedAtDesc(
            @Param("userId") Long userId
    );

    // FIXED METHOD (USED BY TESTS)
    @Query("""
        SELECT r FROM Recommendation r
        WHERE r.userId = :userId
          AND r.generatedAt BETWEEN :start AND :end
        ORDER BY r.generatedAt DESC
    """)
    List<Recommendation> findByUserIdAndGeneratedAtBetween(
            @Param("userId") Long userId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
