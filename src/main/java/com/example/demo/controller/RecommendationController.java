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

    @PostMapping("/generate")
    public Recommendation generate(@RequestParam Long userId) {
        return recommendationService.generate(userId);
    }

    @GetMapping("/latest")
    public List<Long> latest(@RequestParam Long userId) {
        return recommendationService.getLatestRecommendationIds(userId);
    }
}
