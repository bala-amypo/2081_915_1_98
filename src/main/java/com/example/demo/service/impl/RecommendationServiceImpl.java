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

    // ✅ ONLY ONE DEPENDENCY
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public Recommendation save(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    public List<Recommendation> findByUserAndDateRange(
            Long userId,
            LocalDateTime from,
            LocalDateTime to
    ) {
        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, from, to);
    }

    @Override
    public Recommendation findLatestByUser(Long userId) {
        return recommendationRepository
                .findTopByUserIdOrderByGeneratedAtDesc(userId)
                .orElseThrow();
    }
}
