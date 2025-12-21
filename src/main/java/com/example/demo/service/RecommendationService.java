package com.example.demo.service;

import com.example.demo.model.Recommendation;
import java.util.List;

public interface RecommendationService {
    // Use the exact name that your controller calls
    List<Recommendation> getRecommendations();
}
