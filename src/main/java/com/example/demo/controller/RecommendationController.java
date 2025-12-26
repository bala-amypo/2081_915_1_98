package com.example.demo.controller;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    // ===== EXISTING =====
    @GetMapping("/range")
    public List<Recommendation> getRecommendationsInRange(
            @RequestParam Long userId,
            @RequestParam String start,
            @RequestParam String end) {
        return recommendationService.getRecommendationsInRange(userId, start, end);
    }

    // ===== ✅ REQUIRED ADDITION =====
    // POST /recommendations/generate

    @PostMapping("/generate")
    public Recommendation generate(@RequestParam Long userId) {
        return recommendationService.generate(userId); // ✔ matches service
    }

    // ===== ✅ REQUIRED ADDITION =====
    // GET /recommendations/latest

    @GetMapping("/latest")
    public List<Long> getLatest(@RequestParam Long userId) {
        return recommendationService.getLatestRecommendationIds(userId); // ✔ correct
    }
}
