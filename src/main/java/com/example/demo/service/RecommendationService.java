package com.example.demo.service;

import com.example.demo.model.Recommendation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RecommendationService {

    Optional<Recommendation> getLatestRecommendation(Long userId);

    // 🔴 IMPORTANT: return type MUST be List<Long>
    List<Long> getLatestRecommendationIds(Long userId);

    List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );
}
