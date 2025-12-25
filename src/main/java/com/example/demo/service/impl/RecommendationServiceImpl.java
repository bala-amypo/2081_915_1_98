package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    @Autowired
    public RecommendationServiceImpl(
            RecommendationRepository recommendationRepository,
            UserRepository userRepository,
            MicroLessonRepository microLessonRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
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
    public Recommendation getLatestRecommendation(Long userId) {
        return recommendationRepository
                .findByUserIdOrderByGeneratedAtDesc(userId)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("No recommendation found"));
    }

    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {
        Recommendation latest = getLatestRecommendation(userId);
        return latest.parseRecommendedLessonIds();
    }
}
