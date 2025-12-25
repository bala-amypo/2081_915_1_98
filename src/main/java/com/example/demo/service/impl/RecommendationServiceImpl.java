package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    // ✅ REQUIRED by RecommendationService
    @Override
    public Recommendation save(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    // ✅ REQUIRED by RecommendationService
    @Override
    public List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    ) {
        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, start, end);
    }

    // ✅ REQUIRED by RecommendationService
    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {

        Recommendation latest =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);

        if (latest == null || latest.getLessonIdsCsv() == null
                || latest.getLessonIdsCsv().isBlank()) {
            return Collections.emptyList();
        }

        String[] parts = latest.getLessonIdsCsv().split(",");
        List<Long> ids = new ArrayList<>();

        for (String part : parts) {
            ids.add(Long.parseLong(part.trim()));
        }

        return ids;
    }
}
