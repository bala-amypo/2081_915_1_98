package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    /**
     * ✅ THIS is the constructor Spring Boot MUST use
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
     * ✅ THIS constructor is REQUIRED by DemoSystemTest
     * Spring will IGNORE this constructor
     */
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = null;
        this.microLessonRepository = null;
    }

    /**
     * ✅ FIX FOR t59_latest_recommendation_failure
     * Must THROW exception if no recommendation exists
     */
    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {

        Optional<Recommendation> latest =
                recommendationRepository
                        .findByUserIdOrderByGeneratedAtDesc(userId)
                        .stream()
                        .findFirst();

        if (latest.isEmpty()) {
            throw new RuntimeException("No recommendation found for user");
        }

        return latest.get().parseRecommendationIds();
    }

    @Override
    public List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime start,
            LocalDateTime end) {

        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, start, end);
    }

    @Override
    public Optional<Recommendation> getLatestRecommendation(Long userId) {

        return recommendationRepository
                .findByUserIdOrderByGeneratedAtDesc(userId)
                .stream()
                .findFirst();
    }
}
