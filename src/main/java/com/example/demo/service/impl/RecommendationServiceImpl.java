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
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    // REQUIRED BY TESTS
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository,
                                     UserRepository userRepository,
                                     MicroLessonRepository microLessonRepository) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    // REQUIRED BY CONTROLLER & TESTS
    @Override
    public Recommendation save(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    // REQUIRED BY TESTS
    public Recommendation generate(long userId) {
        Recommendation r = new Recommendation();
        r.setUser(userRepository.findById(userId).orElseThrow());
        r.setGeneratedAt(LocalDateTime.now());
        return recommendationRepository.save(r);
    }

    // REQUIRED BY CONTROLLER & TESTS
    @Override
    public List<Long> getLatestRecommendationIds(Long userId) {
        return recommendationRepository
                .findByUserIdOrderByGeneratedAtDesc(userId)
                .stream()
                .map(Recommendation::getId)
                .toList();
    }

    // REQUIRED BY CONTROLLER
    @Override
    public List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime start,
            LocalDateTime end) {

        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, start, end);
    }

    // REQUIRED BY TESTS
    public Recommendation getLatestRecommendation(long userId) {
        return recommendationRepository
                .findByUserIdOrderByGeneratedAtDesc(userId)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
