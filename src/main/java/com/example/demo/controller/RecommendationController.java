package com.example.demo.controller;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // EXISTING service method
    @PostMapping
    public Recommendation save(@RequestBody Recommendation recommendation) {
        return recommendationService.save(recommendation);
    }

    // EXISTING service method
    @GetMapping("/latest")
    public List<Long> latest(@RequestParam Long userId) {
        return recommendationService.getLatestRecommendationIds(userId);
    }
}
