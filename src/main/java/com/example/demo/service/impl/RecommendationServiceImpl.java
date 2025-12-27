package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public Optional<Recommendation> getLatestRecommendation(Long userId) {
        return recommendationRepository
                .findTopByUserIdOrderByGeneratedAtDesc(userId);
    }

    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {
        return recommendationRepository
                .findTopByUserIdOrderByGeneratedAtDesc(userId)
                .map(Recommendation::parseRecommendationIds)
                .orElse(List.of());
    }

    @Override
    public List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime start,
            LocalDateTime end) {

        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, start, end);
    }
}
