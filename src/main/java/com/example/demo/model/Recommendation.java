package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(precision = 5, scale = 2)
    private BigDecimal confidenceScore;

    @Column(length = 1000)
    private String basisSnapshot;

    // CSV field (TEST t42 depends on this)
    @Column(length = 1000)
    private String recommendedLessonIds;

    private LocalDateTime generatedAt;

    @PrePersist
    public void prePersist() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
        if (confidenceScore != null) {
            if (confidenceScore.compareTo(BigDecimal.ZERO) < 0) {
                confidenceScore = BigDecimal.ZERO;
            }
            if (confidenceScore.compareTo(BigDecimal.ONE) > 0) {
                confidenceScore = BigDecimal.ONE;
            }
        }
    }

    // ---------- Getters ----------

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getConfidenceScore() {
        return confidenceScore;
    }

    public String getBasisSnapshot() {
        return basisSnapshot;
    }

    public String getRecommendedLessonIds() {
        return recommendedLessonIds;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    // ---------- CSV PARSER ----------

    public List<Long> parseRecommendedLessonIds() {
        if (recommendedLessonIds == null || recommendedLessonIds.isBlank()) {
            return Collections.emptyList();
        }
        return Arrays.stream(recommendedLessonIds.split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    // ---------- BUILDER ----------

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

        public Builder confidenceScore(BigDecimal score) {
            r.confidenceScore = score;
            return this;
        }

        public Builder basisSnapshot(String snapshot) {
            r.basisSnapshot = snapshot;
            return this;
        }

        public Builder recommendedLessonIds(String csv) {
            r.recommendedLessonIds = csv;
            return this;
        }

        public Builder generatedAt(LocalDateTime time) {
            r.generatedAt = time;
            return this;
        }

        public Recommendation build() {
            return r;
        }
    }
}
