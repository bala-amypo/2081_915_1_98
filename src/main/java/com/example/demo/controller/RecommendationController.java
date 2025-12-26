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
     * POST /recommendations/generate
     */
    @PostMapping("/generate")
    public Recommendation generate(@RequestBody Recommendation recommendation) {
        return recommendationService.save(recommendation);
    }

    /**
     * ✅ GET /recommendations/latest?userId=1
     * Swagger-friendly method
     */
    @GetMapping("/latest")
    public List<Long> getLatest(@RequestParam Long userId) {
        // ✅ CORRECT: returns List<Long>
        return recommendationService.getLatestRecommendationIds(userId);
    }

    /**
     * GET /recommendations/range
     */
    @GetMapping("/range")
    public List<Recommendation> getInRange(
            @RequestParam Long userId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
    ) {
        return recommendationService.getRecommendationsInRange(userId, start, end);
    }
}
