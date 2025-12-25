package com.example.demo.service;

import com.example.demo.model.Recommendation;

import java.util.List;

public interface RecommendationService {

    Recommendation save(Recommendation recommendation);

    List<Recommendation> findAll();
}
