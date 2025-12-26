package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    // ✅ REQUIRED
    @Override
    public Recommendation generateRecommendation(Long userId) {
        Recommendation r = new Recommendation();
        r.setUserId(userId);
        r.setGeneratedAt(LocalDateTime.now());
        return recommendationRepository.save(r);
    }

    // ✅ REQUIRED
    @Override
    public Recommendation getLatestRecommendation(Long userId) {
        return recommendationRepository
                .findTopByUserIdOrderByGeneratedAtDesc(userId)
                .orElse(null);
    }
}
