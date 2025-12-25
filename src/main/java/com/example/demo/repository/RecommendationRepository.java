package com.example.demo.repository;

import com.example.demo.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    @Query("""
        SELECT r FROM Recommendation r
        WHERE r.user.id = :userId
        ORDER BY r.generatedAt DESC
    """)
    List<Recommendation> findByUserIdOrderByGeneratedAtDesc(
            @Param("userId") Long userId
    );
}
