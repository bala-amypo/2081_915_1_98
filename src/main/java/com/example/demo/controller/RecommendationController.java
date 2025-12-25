package com.example.demo.controller;

import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/latest/{userId}")
    public List<Long> getLatestRecommendation(@PathVariable Long userId) {
        return recommendationService.getLatestRecommendationIds(userId);
    }
}
