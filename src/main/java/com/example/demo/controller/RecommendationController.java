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

    @PostMapping
    public Recommendation save(@RequestBody Recommendation recommendation) {
        return recommendationService.save(recommendation);
    }

    @GetMapping("/latest")
    public List<Long> latest(@RequestParam Long userId) {
        return recommendationService.getLatestRecommendationIds(userId);
    }

    @GetMapping("/{UserId}")
    public List<Recommendation> range(
            @RequestParam Long userId,
            @RequestParam String start,
            @RequestParam String end) {

        return recommendationService.getRecommendationsInRange(
                userId,
                LocalDateTime.parse(start),
                LocalDateTime.parse(end)
        );
    }
}
