package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    /**
     * ✅ Constructor used by SPRING BOOT
     * Only ONE constructor must be @Autowired
     */
    @Autowired
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    /**
     * ✅ Constructor used by TESTS (DO NOT annotate)
     */
    public RecommendationServiceImpl(
            RecommendationRepository recommendationRepository,
            UserRepository userRepository,
            MicroLessonRepository microLessonRepository
    ) {
        this.recommendationRepository = recommendationRepository;
    }

    // ============================
    // Service Methods
    // ============================

    @Override
    public Recommendation save(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {
        List<Recommendation> list =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);

        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        return list.get(0).parseRecommendationIds();
    }

    /**
     * ✅ Required for test t59_latest_recommendation_failure
     * Must return Optional.empty() when no recommendation exists
     */
    public Optional<Recommendation> getLatestRecommendation(Long userId) {
        List<Recommendation> list =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);

        if (list == null || list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
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
}
