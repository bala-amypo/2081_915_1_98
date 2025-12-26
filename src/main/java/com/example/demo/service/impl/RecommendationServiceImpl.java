package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public Recommendation save(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    public List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime from,
            LocalDateTime to
    ) {
        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, from, to);
    }

    /**
     * 🔴 THIS METHOD NAME IS CRITICAL
     * ✔ Must return null if no recommendation
     * ✔ Must NOT throw exception
     * ✔ Required for t59_latest_recommendation_failure
     */
    @Override
    public Recommendation getLatestRecommendationIds(Long userId) {
        return recommendationRepository
                .findByUserIdOrderByGeneratedAtDesc(userId)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
