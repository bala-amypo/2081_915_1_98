package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    /**
     * REQUIRED by RecommendationService
     * Used by t59_latest_recommendation_failure
     */
    @Override
    public Optional<Recommendation> getLatestRecommendation(Long userId) {
        return recommendationRepository
                .findTopByUserIdOrderByGeneratedAtDesc(userId);
    }

    /**
     * REQUIRED by RecommendationService
     * Returns ONLY lesson IDs (CSV converted to List<Long>)
     */
    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {

        Optional<Recommendation> latest =
                recommendationRepository.findTopByUserIdOrderByGeneratedAtDesc(userId);

        return latest
                .map(Recommendation::parseRecommendationIds)
                .orElse(List.of());
    }

    /**
     * REQUIRED by RecommendationService
     * Used by t58_hql_recommendation_range
     */
    @Override
    public List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime start,
            LocalDateTime end) {

        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, start, end);
    }
}
