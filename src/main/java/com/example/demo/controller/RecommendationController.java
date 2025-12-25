package com.example.demo.controller;

import com.example.demo.model.Recommendation;
import com.example.demo.service.ServiceImpl.RecommendationServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationServiceImpl recommendationService;

    public RecommendationController(RecommendationServiceImpl recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/latest/{userId}")
    public Recommendation getLatest(@PathVariable Long userId) {
        return recommendationService.getLatestRecommendation(userId);
    }
}
