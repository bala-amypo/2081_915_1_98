package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    // ✅ Constructor used by SPRING
    public RecommendationServiceImpl(
            RecommendationRepository recommendationRepository
    ) {
        this.recommendationRepository = recommendationRepository;
    }

    // ✅ Constructor used by TESTS (REQUIRED)
    public RecommendationServiceImpl(
            RecommendationRepository recommendationRepository,
            UserRepository userRepository,
            MicroLessonRepository microLessonRepository
    ) {
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

    // ❗ helper method (NOT in interface → no @Override)
    public Recommendation getLatestRecommendation(Long userId) {
        List<Recommendation> list =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);

        if (list.isEmpty()) {
            throw new RuntimeException("No recommendation found for user");
        }

        return list.get(0);
    }

    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {
        Recommendation latest = getLatestRecommendation(userId);
        return latest.parseRecommendedLessonIds();
    }
}
