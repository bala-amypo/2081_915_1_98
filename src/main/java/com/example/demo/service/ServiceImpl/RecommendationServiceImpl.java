package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public List<Recommendation> getRecommendations() {
        // TODO: return actual recommendations
        return new ArrayList<>();
    }
}
