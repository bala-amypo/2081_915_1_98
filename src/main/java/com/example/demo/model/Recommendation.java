package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =======================
       RELATIONSHIPS
       ======================= */

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* =======================
       DATA FIELDS
       ======================= */

    @Column(name = "recommended_lesson_ids", columnDefinition = "TEXT")
    private String recommendedLessonIds; // CSV: "1,2,3"

    @Column(name = "confidence_score", precision = 5, scale = 2)
    private BigDecimal confidenceScore;

    @Column(name = "basis_snapshot", columnDefinition = "TEXT")
    private String basisSnapshot;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    /* =======================
       LIFECYCLE
       ======================= */

    @PrePersist
    public void prePersist() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
    }

    /* =======================
       CSV HELPERS (USED IN TESTS)
       ======================= */

    public List<Long> parseRecommendationIds() {
        if (recommendedLessonIds == null || recommendedLessonIds.isBlank()) {
            return List.of();
        }
        return Arrays.stream(recommendedLessonIds.split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    /* =======================
       GETTERS & SETTERS
       ======================= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // REQUIRED by tests
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {   // REQUIRED (missing earlier)
        this.user = user;
    }

    public String getRecommendedLessonIds() {
        return recommendedLessonIds;
    }

    public void setRecommendedLessonIds(String recommendedLessonIds) {
        this.recommendedLessonIds = recommendedLessonIds;
    }

    public BigDecimal getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(BigDecimal confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public String getBasisSnapshot() {
        return basisSnapshot;
    }

    public void setBasisSnapshot(String basisSnapshot) {
        this.basisSnapshot = basisSnapshot;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    /* =======================
       BUILDER (MANDATORY FOR TESTS)
       ======================= */

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final Recommendation r = new Recommendation();

        public Builder id(Long id) {
            r.setId(id);
            return this;
        }

        public Builder user(User user) {
            r.setUser(user);
            return this;
        }

        public Builder recommendedLessonIds(String ids) {
            r.setRecommendedLessonIds(ids);
            return this;
        }

        public Builder confidenceScore(BigDecimal score) {
            r.setConfidenceScore(score);
            return this;
        }

        public Builder basisSnapshot(String snapshot) {
            r.setBasisSnapshot(snapshot);
            return this;
        }

        public Builder generatedAt(LocalDateTime time) {
            r.setGeneratedAt(time);
            return this;
        }

        public Recommendation build() {
            return r;
        }
    }
}
