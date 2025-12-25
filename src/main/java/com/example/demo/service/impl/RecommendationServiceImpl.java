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
            LocalDateTime start,
            LocalDateTime end
    ) {
        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, start, end);
    }

    /**
     * REQUIRED BY t59_latest_recommendation_failure
     * Must THROW exception if no recommendation exists
     */
    @Override
    public Recommendation getLatestRecommendation(Long userId) {
        List<Recommendation> list =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);

        if (list.isEmpty()) {
            throw new RuntimeException("No recommendation found for user");
        }

        return list.get(0);
    }

    /**
     * REQUIRED BY t42_recommendation_ids_csv
     */
    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {
        Recommendation latest = getLatestRecommendation(userId);
        return latest.parseRecommendedLessonIds();
    }
}
