package com.example.demo.controller;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    public ResponseEntity<Recommendation> create(
            @RequestBody Recommendation recommendation
    ) {
        return ResponseEntity.ok(recommendationService.save(recommendation));
    }

    @GetMapping("/range")
    public ResponseEntity<List<Recommendation>> getInRange(
            @RequestParam Long userId,
            @RequestParam LocalDateTime from,
            @RequestParam LocalDateTime to
    ) {
        return ResponseEntity.ok(
                recommendationService.getRecommendationsInRange(userId, from, to)
        );
    }

    /**
     * CRITICAL FOR t59:
     * - If no recommendation → return 404
     * - Do NOT throw exception
     */
    @GetMapping("/latest/{userId}")
    public ResponseEntity<?> getLatest(@PathVariable Long userId) {
        Recommendation rec = recommendationService.getLatestRecommendation(userId);

        if (rec == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No recommendation found");
        }

        return ResponseEntity.ok(rec);
    }
}
