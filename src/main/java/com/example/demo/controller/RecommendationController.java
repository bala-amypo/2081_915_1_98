// package com.example.demo.controller;

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

    // ===================== FIX FOR t59 =====================
    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestParam Long userId) {
        try {
            List<Long> ids = recommendationService.getLatestRecommendationIds(userId);
            return ResponseEntity.ok(ids);
        } catch (Exception e) {
            // ✅ Explicit failure response
            return ResponseEntity.badRequest().body("No recommendation found");
        }
    }
    // ======================================================

    @GetMapping("/latest")
    public ResponseEntity<?> getLatest(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(
                    recommendationService.getLatestRecommendationIds(userId)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No recommendation found");
        }
    }

    @GetMapping("/range")
    public List<Recommendation> getInRange(
            @RequestParam Long userId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        return recommendationService.getRecommendationsInRange(userId, start, end);
    }
}
