package com.example.demo.service;

import com.example.demo.model.Recommendation;

import java.time.LocalDateTime;
import java.util.List;

public interface RecommendationService {

    // Save a recommendation
    Recommendation save(Recommendation recommendation);

    // Get recommendations for a user within a date range
    List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );

    // Get latest recommendation lesson IDs as a list
    List<Long> getLatestRecommendationIds(Long userId);

    // ✅ REQUIRED to fix @Override error and Swagger startup issue
    Recommendation getLatestRecommendation(Long userId);
}
