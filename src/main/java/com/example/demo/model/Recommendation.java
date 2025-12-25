package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    /** ✅ CSV snapshot required by t42 */
    private String basisSnapshot;

    private LocalDateTime generatedAt;

    /* =======================
       JPA Lifecycle
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

        /** CSV input */
        public Builder recommendedLessonIds(String csv) {
            r.basisSnapshot = csv;
            r.recommendedLessonIds = List.of(csv.split(","))
                    .stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            return this;
        }

        /** ✅ FIX: List input must ALSO create CSV */
        public Builder recommendedLessonIds(List<Long> ids) {
            r.recommendedLessonIds = ids;
            r.basisSnapshot = ids.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            return this;
        }

        public Builder basisSnapshot(String snapshot) {
            r.basisSnapshot = snapshot;
            return this;
        }

        public Builder confidenceScore(BigDecimal confidenceScore) {
            r.confidenceScore = confidenceScore;
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
