package com.example.demo.controller;

import com.example.demo.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * Generate latest recommendation IDs
     * Swagger-friendly + test-safe
     */
    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(
                    recommendationService.getLatestRecommendationIds(userId)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No recommendation found");
        }
    }

    /**
     * Get latest recommendation IDs
     */
    @GetMapping("/latest")
    public ResponseEntity<?> latest(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(
                    recommendationService.getLatestRecommendationIds(userId)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No recommendation found");
        }
    }

    /**
     * Get recommendations in date range
     */
    @GetMapping("/range")
    public ResponseEntity<?> range(
            @RequestParam Long userId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        return ResponseEntity.ok(
                recommendationService.getRecommendationsInRange(userId, start, end)
        );
    }
}
