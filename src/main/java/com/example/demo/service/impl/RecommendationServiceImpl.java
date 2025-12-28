package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    /**
     * ✅ THIS constructor is used by Spring Boot
     * We MUST mark it with @Autowired
     */
    @Autowired
    public RecommendationServiceImpl(
            RecommendationRepository recommendationRepository,
            UserRepository userRepository,
            MicroLessonRepository microLessonRepository) {

        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    /**
     * ✅ Required by DemoSystemTest (manual object creation)
     * ❌ Spring will IGNORE this constructor
     */
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = null;
        this.microLessonRepository = null;
    }

    /**
     * ✅ FIXES t59_latest_recommendation_failure
     * If no recommendation exists → return EMPTY LIST
     */
    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {

        Optional<Recommendation> latest =
                recommendationRepository
                        .findByUserIdOrderByGeneratedAtDesc(userId)
                        .stream()
                        .findFirst();

        return latest
                .map(Recommendation::parseRecommendationIds)
                .orElse(Collections.emptyList());
    }

    /**
     * ✅ Used by t58 (range query)
     */
    @Override
    public List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime start,
            LocalDateTime end) {

        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, start, end);
    }

    /**
     * ✅ Used internally by tests
     */
    @Override
    public Optional<Recommendation> getLatestRecommendation(Long userId) {

        return recommendationRepository
                .findByUserIdOrderByGeneratedAtDesc(userId)
                .stream()
                .findFirst();
    }
}
