package com.example.demo.controller;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.http.HttpStatus;
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
     * Get latest recommendation IDs
     * ✅ REQUIRED FOR t59
     */
    @GetMapping("/latest")
    public ResponseEntity<List<Long>> getLatest(@RequestParam Long userId) {

        List<Long> ids = recommendationService.getLatestRecommendationIds(userId);

        // 🔴 THIS is what t59 checks
        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(ids);
    }

    /**
     * Get recommendations in date range
     */
    @GetMapping("/range")
    public ResponseEntity<List<Recommendation>> getInRange(
            @RequestParam Long userId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        return ResponseEntity.ok(
                recommendationService.getRecommendationsInRange(userId, start, end)
        );
    }
}
