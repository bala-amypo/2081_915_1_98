package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String recommendedContent;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getRecommendedContent() { return recommendedContent; }
    public void setRecommendedContent(String recommendedContent) {
        this.recommendedContent = recommendedContent;
    }
}
