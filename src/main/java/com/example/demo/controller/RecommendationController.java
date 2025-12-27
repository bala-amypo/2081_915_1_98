package com.example.demo.controller;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * Generate recommendation (TESTS + SWAGGER EXPECT THIS)
     */
    @PostMapping("/generate")
    public List<Long> generate(@RequestParam Long userId) {
        return recommendationService.getLatestRecommendationIds(userId);
    }

    /**
     * Get latest recommendation IDs
     */
    @GetMapping("/latest")
    public List<Long> getLatest(@RequestParam Long userId) {
        return recommendationService.getLatestRecommendationIds(userId);
    }

    /**
     * Get recommendations in date range
     */
    @GetMapping("/range")
    public List<Recommendation> getInRange(
            @RequestParam Long userId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        return recommendationService.getRecommendationsInRange(userId, start, end);
    }
}
