package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ElementCollection
    @CollectionTable(
            name = "recommendation_lessons",
            joinColumns = @JoinColumn(name = "recommendation_id")
    )
    @Column(name = "lesson_id")
    private List<Long> recommendedLessonIds;

    @Column(precision = 5, scale = 2)
    private BigDecimal confidenceScore;

    @Column(length = 1000)
    private String basisSnapshot;

    private LocalDateTime generatedAt;

    // =========================
    // JPA CALLBACK
    // =========================
    @PrePersist
    public void prePersist() {
        if (generatedAt == null) {
            this.generatedAt = LocalDateTime.now();
        }
    }

    // =========================
    // GETTERS
    // =========================
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Long> getRecommendedLessonIds() {
        return recommendedLessonIds;
    }

    public BigDecimal getConfidenceScore() {
        return confidenceScore;
    }

    public String getBasisSnapshot() {
        return basisSnapshot;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    // =========================
    // BUILDER
    // =========================
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final Recommendation r = new Recommendation();

        public Builder id(Long id) {
            r.id = id;
            return this;
        }

        public Builder userId(Long userId) {
            r.userId = userId;
            return this;
        }

        // ✅ ACCEPT List<Long>
        public Builder recommendedLessonIds(List<Long> ids) {
            r.recommendedLessonIds = ids;
            return this;
        }

        // ✅ ACCEPT String "1,2,3" (FOR TEST)
        public Builder recommendedLessonIds(String ids) {
            r.recommendedLessonIds = Arrays.stream(ids.split(","))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            return this;
        }

        public Builder confidenceScore(BigDecimal score) {
            r.confidenceScore = score;
            return this;
        }

        public Builder basisSnapshot(String snapshot) {
            r.basisSnapshot = snapshot;
            return this;
        }

        // ✅ REQUIRED BY TEST
        public Builder generatedAt(LocalDateTime time) {
            r.generatedAt = time;
            return this;
        }

        public Recommendation build() {
            return r;
        }
    }
}
