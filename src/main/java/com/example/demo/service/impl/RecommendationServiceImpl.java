package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            LocalDateTime start,
            LocalDateTime end
    ) {
        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, start, end);
    }

    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {
        Recommendation latest =
                recommendationRepository.findTopByUserIdOrderByGeneratedAtDesc(userId)
                        .orElse(null);

        if (latest == null || latest.getRecommendationIdsCsv() == null) {
            return List.of();
        }

        return Arrays.stream(latest.getRecommendationIdsCsv().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }
}
