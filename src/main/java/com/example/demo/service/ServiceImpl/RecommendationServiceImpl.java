package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository repository;

    public RecommendationServiceImpl(RecommendationRepository repository) {
        this.repository = repository;
    }

    public List<Recommendation> getRecommendations() {
        return repository.findAll();
    }
}
