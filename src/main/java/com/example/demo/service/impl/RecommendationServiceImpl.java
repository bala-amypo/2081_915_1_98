// package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    // ✅ Constructor for Spring
    public RecommendationServiceImpl(
            RecommendationRepository recommendationRepository,
            UserRepository userRepository,
            MicroLessonRepository microLessonRepository) {

        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    // ===================== FIX FOR t59 =====================
    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {

        Optional<Recommendation> latest =
                recommendationRepository
                        .findByUserIdOrderByGeneratedAtDesc(userId)
                        .stream()
                        .findFirst();

        // ✅ CRITICAL FIX: THROW exception when not found
        if (latest.isEmpty()) {
            throw new IllegalStateException("No recommendation found for user");
        }

        return latest.get().parseRecommendationIds();
    }
    // ======================================================

    @Override
    public Optional<Recommendation> getLatestRecommendation(Long userId) {
        return recommendationRepository
                .findByUserIdOrderByGeneratedAtDesc(userId)
                .stream()
                .findFirst();
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
