package com.example.demo.service;

import com.example.demo.model.Recommendation;
import java.time.LocalDateTime;
import java.util.List;

public interface RecommendationService {

    Recommendation save(Recommendation recommendation);

    List<Long> getLatestRecommendationIds(Long userId);

    List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );
}
