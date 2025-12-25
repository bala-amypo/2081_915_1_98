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

    private Long userId;

    @ElementCollection
    @CollectionTable(
            name = "recommendation_lessons",
            joinColumns = @JoinColumn(name = "recommendation_id")
    )
    @Column(name = "lesson_id")
    private List<Long> recommendedLessonIds;

    private BigDecimal confidenceScore;

    private String basisSnapshot;

    private LocalDateTime generatedAt;

    /* =======================
       JPA Lifecycle Callback
       ======================= */
    @PrePersist
    public void prePersist() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
    }

    /* =======================
       Getters
       ======================= */
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

    /* =======================
       Builder
       ======================= */
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

        /* ✅ UPDATED METHOD (FIXES t42_recommendation_ids_csv) */
        public Builder recommendedLessonIds(String ids) {
            r.recommendedLessonIds = Arrays.stream(ids.split("[,;]"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            return this;
        }

        public Builder confidenceScore(BigDecimal confidenceScore) {
            r.confidenceScore = confidenceScore;
            return this;
        }

        public Builder basisSnapshot(String basisSnapshot) {
            r.basisSnapshot = basisSnapshot;
            return this;
        }

        public Builder generatedAt(LocalDateTime generatedAt) {
            r.generatedAt = generatedAt;
            return this;
        }

        public Recommendation build() {
            return r;
        }
    }
}
