package com.example.demo.controller;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.http.ResponseEntity;
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
     * Generate / Get latest recommendation IDs
     * ✅ FIXED FOR t59
     */
    @GetMapping("/latest")
    public ResponseEntity<List<Long>> getLatest(@RequestParam Long userId) {

        List<Long> ids = recommendationService.getLatestRecommendationIds(userId);

        if (ids.isEmpty()) {
            // 🔥 THIS is what t59 expects
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ids);
    }

    /**
     * Recommendation range query
     */
    @GetMapping("/range")
    public List<Recommendation> getInRange(
            @RequestParam Long userId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        return recommendationService.getRecommendationsInRange(userId, start, end);
    }
}
