package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User for whom recommendation is generated
    @Column(nullable = false)
    private Long userId;

    /**
     * Stores recommended lesson IDs as CSV
     * Example: "1,2,5,9"
     */
    @Column(name = "recommendation_ids_csv", nullable = false, length = 1000)
    private String recommendationIdsCsv;

    /**
     * Snapshot / basis used to generate recommendation
     * (used by tests like basisSnapshot)
     */
    @Column(length = 1000)
    private String basisSnapshot;

    /**
     * When recommendation was generated
     */
    @Column(nullable = false)
    private LocalDateTime generatedAt;

    /* =========================
       JPA Lifecycle Hook
       ========================= */
    @PrePersist
    public void prePersist() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
    }

    /* =========================
       Getters and Setters
       ========================= */

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRecommendationIdsCsv() {
        return recommendationIdsCsv;
    }

    public String getBasisSnapshot() {
        return basisSnapshot;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRecommendationIdsCsv(String recommendationIdsCsv) {
        this.recommendationIdsCsv = recommendationIdsCsv;
    }

    public void setBasisSnapshot(String basisSnapshot) {
        this.basisSnapshot = basisSnapshot;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    /* =========================
       Builder (USED BY TESTS)
       ========================= */

    public static class Builder {

        private final Recommendation recommendation = new Recommendation();

        public Builder userId(Long userId) {
            recommendation.setUserId(userId);
            return this;
        }

        public Builder recommendationIdsCsv(String csv) {
            recommendation.setRecommendationIdsCsv(csv);
            return this;
        }

        public Builder basisSnapshot(String snapshot) {
            recommendation.setBasisSnapshot(snapshot);
            return this;
        }

        public Builder generatedAt(LocalDateTime time) {
            recommendation.setGeneratedAt(time);
            return this;
        }

        public Recommendation build() {
            return recommendation;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
