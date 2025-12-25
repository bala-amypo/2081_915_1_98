package com.example.demo.service;

import com.example.demo.model.Recommendation;

public interface RecommendationService {

    Recommendation save(Recommendation recommendation);

    Recommendation getLatestRecommendation(Long userId);
}
