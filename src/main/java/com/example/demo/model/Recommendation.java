package com.example.demo.model;

public class Recommendation {
    private Long userId;
    private String recommendedContent;

    public Recommendation() {}
    public Recommendation(Long userId, String recommendedContent) {
        this.userId = userId; this.recommendedContent = recommendedContent;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getRecommendedContent() { return recommendedContent; }
    public void setRecommendedContent(String recommendedContent) { this.recommendedContent = recommendedContent; }
}
