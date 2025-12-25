package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double confidenceScore;

    @Column(length = 2000)
    private String basisSnapshot;

    @ElementCollection
    private List<Long> recommendedLessonIds;

    private LocalDateTime generatedAt;

    // ✅ REQUIRED BY TEST
    @PrePersist
    public void prePersist() {
        this.generatedAt = LocalDateTime.now();
    }

    // ====== GETTERS ======
    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public String getBasisSnapshot() {
        return basisSnapshot;
    }

    public List<Long> getRecommendedLessonIds() {
        return recommendedLessonIds;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    // ====== BUILDER (TEST EXPECTS THIS) ======
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Recommendation r = new Recommendation();

        public Builder confidenceScore(Double confidenceScore) {
            r.confidenceScore = confidenceScore;
            return this;
        }

        public Builder basisSnapshot(String basisSnapshot) {
            r.basisSnapshot = basisSnapshot;
            return this;
        }

        public Builder recommendedLessonIds(List<Long> ids) {
            r.recommendedLessonIds = ids;
            return this;
        }

        public Recommendation build() {
            return r;
        }
    }
}
