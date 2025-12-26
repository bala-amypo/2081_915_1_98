package com.example.demo.service;

import com.example.demo.model.Recommendation;

public interface RecommendationService {

    // ✅ REQUIRED
    Recommendation generateRecommendation(Long userId);

    // ✅ REQUIRED
    Recommendation getLatestRecommendation(Long userId);
}
